package org.klaptech.limechat.client.gui.dialogs;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.components.CaptchaView;
import org.klaptech.limechat.client.gui.components.maskfield.MaskInputView;
import org.klaptech.limechat.client.gui.components.maskfield.validators.EmailValidator;
import org.klaptech.limechat.client.gui.components.maskfield.validators.LengthValidator;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.shared.client.ClientMessageFactory;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Login/Register dialog
 * Contains: login field, password field, default room selector, remember password check.
 * Register and login mode can be switched by using tabs
 *
 * @author rlapin
 */
public class LoginDialog implements Dialog {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private static final Logger LOGGER = getLogger(LoginDialog.class.getName());
    private static final String LIME_CHAT_ICON_64x64 = "images/limechat_64x64.png";
    private final ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    ObservableList<String> roomList =
            FXCollections.observableArrayList(
                    "Room 219",
                    "TestRoom1",
                    "TestRoom2"
            );
    private Stage stage;
    private ResourceBundle resourceBundle;
    private TabPane loginTabPane;
    private LoginPane loginPane;
    private RegisterPane registerPane;


    public LoginDialog() {
        stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_ICON_64x64)));
        resourceBundle = ResourceBundle.getBundle(getClass().getCanonicalName());
        stage.setTitle(resourceBundle.getString("login"));
        initComponents();
        initListeners();
    }

    private void initListeners() {

    }


    private void initComponents() {
        loginPane = new LoginPane();
        registerPane = new RegisterPane();
        loginTabPane = new TabPane(loginPane, registerPane);
        loginTabPane.setSide(Side.TOP);
        loginTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        GUIUtils.addCss(scene, "fxml/login.css");
        stage.setScene(scene);
        borderPane.setCenter(loginTabPane);


    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Fired when user logged
     *
     * @param loginType {@link LoginAnswerType}
     */
    public void userLogged(LoginAnswerType loginType) {
        lock.lock();
        condition.signal();
        lock.unlock();
        Platform.runLater(() -> {
            switch (loginType) {
                case SUCCESS:
                    hide();
                    GUIManager.getInstance().showSplashScreen();
                    break;
                case USER_NOT_EXISTS:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("usernotexists"), Dialogs.IconType.ERROR);
                    break;
                case USER_ALREADY_CON:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("useralreadyconnects"), Dialogs.IconType.ERROR);
                    break;
                case INCORRECT_PASSWORD:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("incorrectpassword"), Dialogs.IconType.ERROR);
                    break;
                default:
            }
        });
    }

    /**
     * Fired when user registered
     *
     * @param registerAnswerType {@link RegisterAnswerType}
     */
    public void userRegistered(RegisterAnswerType registerAnswerType) {
        lock.lock();
        condition.signal();
        lock.unlock();
        Platform.runLater(() -> {
            switch (registerAnswerType) {
                case SUCCESS:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("info"), resourceBundle.getString("registrationsuccess"), Dialogs.IconType.INFO);
                    loginTabPane.getSelectionModel().select(loginPane);
                    clearAllComponents();
                    loginPane.loginMaskView.getTextField().setText(registerPane.loginMaskView.getText());
                    loginPane.passwordMaskView.getTextField().setText("");
                    break;
                case USER_ALREADY_REGISTER:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("useralreadyexists"), Dialogs.IconType.ERROR);
                    break;
                case ERROR:
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("registererror"), Dialogs.IconType.ERROR);
                    break;
                default:
            }
        });
    }

    private void clearAllComponents() {
        loginPane.loginMaskView.getTextField().setText("");
        loginPane.passwordMaskView.getTextField().setText("");
        registerPane.loginMaskView.getTextField().setText("");
        registerPane.passwordMaskView.getTextField().setText("");
        registerPane.confirmPasswordMaskView.getTextField().setText("");
        registerPane.emailMaskView.getTextField().setText("");
        registerPane.captchaTextField.setText("");

    }

    /**
     * LoginTab. User input name , password and select default room, then connect to server
     */
    private class LoginPane extends Tab {
        private MaskInputView loginMaskView;
        private MaskInputView passwordMaskView;
        private ComboBox<String> defaultRoomCmb;
        private CheckBox rememberChb;
        private Label fillerLabel;
        private Label loginLabel;
        private Button loginButton;

        public LoginPane() {

            setText(resourceBundle.getString("login"));
            initComponents();
            initListeners();
        }

        private void initComponents() {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            loginLabel = new Label(resourceBundle.getString("name"));
            gridPane.add(loginLabel, 0, 0);
            TextField loginField = new TextField();
            loginMaskView = new MaskInputView(loginField, new LengthValidator(5));
            loginField.setPromptText(resourceBundle.getString("inputlogin"));
            gridPane.add(loginField, 1, 0);
            Label passwordLabel = new Label(resourceBundle.getString("password"));
            gridPane.add(passwordLabel, 0, 1);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText(resourceBundle.getString("inputpwd"));
            passwordMaskView = new MaskInputView(passwordField, new LengthValidator(5));
            gridPane.add(passwordField, 1, 1);
            Label defaultRoomLabel = new Label(resourceBundle.getString("defaultRoom"));
            gridPane.add(defaultRoomLabel, 0, 2);
            defaultRoomCmb = new ComboBox<>(roomList);

            gridPane.add(defaultRoomCmb, 1, 2);
            fillerLabel = new Label();
            gridPane.add(fillerLabel, 0, 3);
            rememberChb = new CheckBox(resourceBundle.getString("rememberUser"));
            gridPane.add(rememberChb, 1, 3);
            loginButton = new Button(resourceBundle.getString("login"));
            gridPane.add(new Label(), 0, 4);
            gridPane.add(loginButton, 1, 4);
            setContent(gridPane);
        }

        private void initListeners() {
            TextField loginField = loginMaskView.getTextField();
            defaultRoomCmb.prefWidthProperty().bind(loginField.widthProperty());
            rememberChb.prefWidthProperty().bind(loginField.widthProperty());
            fillerLabel.prefWidthProperty().bind(loginLabel.widthProperty());

            loginButton.setOnAction(event -> {
                String login = loginMaskView.getText();
                String password = passwordMaskView.getText();
                if (login.isEmpty() || password.isEmpty()) {
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("checkinputdata"), Dialogs.IconType.ERROR);
                } else {
                    try {
                        ServerConnector.INSTANCE.write(ClientMessageFactory.createLoginMessage(login, password.getBytes()));
                        try {
                            lock.lock();
                            condition.await(ServerConnector.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                            lock.unlock();
                        } catch (InterruptedException e) {
                            Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("timeout"), Dialogs.IconType.ERROR);
                        }
                    } catch (IOException e) {
                        Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("autherror"), Dialogs.IconType.ERROR);
                    }
                }
            });
        }


    }

    /**
     * RegisterTab for registration user on server
     */
    private class RegisterPane extends Tab {
        private MaskInputView emailMaskView;
        private Button registerButton;
        private MaskInputView loginMaskView;
        private MaskInputView passwordMaskView;
        private MaskInputView confirmPasswordMaskView;
        private CaptchaView captcha;
        private TextField captchaTextField;

        public RegisterPane() {
            setText(resourceBundle.getString("register"));
            initComponents();
            initListeners();
        }

        private void initComponents() {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER); //TODO define textfields height in css
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            Label loginLabel = new Label(resourceBundle.getString("name"));
            gridPane.add(loginLabel, 0, 0);
            TextField loginField = new TextField();
            loginMaskView = new MaskInputView(loginField, new LengthValidator(5));
            loginField.setPromptText(resourceBundle.getString("inputlogin"));
            gridPane.add(loginField, 1, 0);
            Label passwordLabel = new Label(resourceBundle.getString("password"));
            gridPane.add(passwordLabel, 0, 1);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText(resourceBundle.getString("inputpwd"));
            passwordMaskView = new MaskInputView(passwordField, new LengthValidator(5));
            gridPane.add(passwordField, 1, 1);
            Label confirmPasswordLabel = new Label(resourceBundle.getString("confirmpwd"));
            gridPane.add(confirmPasswordLabel, 0, 2);
            PasswordField confirmPasswordField = new PasswordField();
            confirmPasswordMaskView = new MaskInputView(confirmPasswordField, new LengthValidator(5));
            confirmPasswordField.setPromptText(resourceBundle.getString("repeatpwd"));
            gridPane.add(confirmPasswordField, 1, 2);
            Label emailLabel = new Label(resourceBundle.getString("email"));
            TextField emailTextField = new TextField();
            emailTextField.setPromptText(resourceBundle.getString("inputemail"));
            emailMaskView = new MaskInputView(emailTextField, new EmailValidator());
            gridPane.add(emailLabel, 0, 3);
            gridPane.add(emailMaskView.getTextField(), 1, 3);
            captcha = new CaptchaView(100, 100);
            gridPane.add(captcha.getCanvas(), 0, 4, 1, 2);
            Label captchaLabel = new Label(resourceBundle.getString("captcha"));
            gridPane.add(captchaLabel, 1, 4);
            captchaTextField = new TextField();
            gridPane.add(captchaTextField, 1, 5);
            registerButton = new Button(resourceBundle.getString("register"));
            gridPane.add(new Label(), 0, 6);
            gridPane.add(registerButton, 1, 6);
            setContent(gridPane);
        }

        private void initListeners() {
            registerButton.setOnAction(event -> {
                String login = loginMaskView.getText();
                String password = passwordMaskView.getText();
                String email = emailMaskView.getText();
                if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("checkinputdata"), Dialogs.IconType.ERROR);
                } else {
                    if (!captchaTextField.getText().equals(captcha.getValue())) {
                        Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("incorrectcaptcha"), Dialogs.IconType.ERROR);
                        captcha.generateValue();
                    } else {
                        try {
                            ServerConnector.INSTANCE.write(ClientMessageFactory.createRegisterMessage(login, password.getBytes(), email));
                            try {
                                lock.lock();
                                condition.await(ServerConnector.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                                lock.unlock();
                            } catch (InterruptedException e) {
                                Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("timeout"), Dialogs.IconType.ERROR);
                            }
                        } catch (IOException e) {
                            Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("error"), resourceBundle.getString("registererror"), Dialogs.IconType.ERROR);
                        }
                    }
                }
            });
        }


    }

}
