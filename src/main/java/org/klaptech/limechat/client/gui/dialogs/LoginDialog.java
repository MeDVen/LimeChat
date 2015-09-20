package org.klaptech.limechat.client.gui.dialogs;

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
import org.klaptech.limechat.client.gui.components.CaptchaView;
import org.klaptech.limechat.client.gui.components.maskfield.MaskInputView;
import org.klaptech.limechat.client.gui.components.maskfield.validators.EmailValidator;
import org.klaptech.limechat.client.gui.components.maskfield.validators.LengthValidator;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Login/Register dialog
 * Contains: login field, password field, default room selector, remember password check.
 * Register and login mode can be switched by using tabs
 *
 * @author rlapin
 */
public class LoginDialog {
    private static final Logger LOGGER = getLogger(LoginDialog.class.getName());
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private static final String LIME_CHAT_ICON_64x64 = "images/limechat_64x64.png";
    private Stage stage;
    private ResourceBundle resourceBundle;
    ObservableList<String> roomList =
            FXCollections.observableArrayList(
                    "Room 219",
                    "TestRoom1",
                    "TestRoom2"
            );
    private TextField captchaTextField;

    public LoginDialog() {
        stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_ICON_64x64)));
        resourceBundle = ResourceBundle.getBundle("org.klaptech.limechat.client.gui.dialogs.LoginDialog");
        stage.setTitle(resourceBundle.getString("login"));
        initComponents();
        initListeners();
    }

    private void initListeners() {

    }


    private void initComponents() {
        TabPane loginTabPane = new TabPane(new LoginPane(), new RegisterPane());
        loginTabPane.setSide(Side.TOP);
        loginTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/login.css").toExternalForm());
        } catch (NullPointerException e) {
            LOGGER.severe("Error while loading file: login.css");
        }
        stage.setScene(scene);
        borderPane.setCenter(loginTabPane);


    }


    public void show() {
        stage.show();
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
            TextField passwordField = passwordMaskView.getTextField();
            defaultRoomCmb.prefWidthProperty().bind(loginField.widthProperty());
            rememberChb.prefWidthProperty().bind(loginField.widthProperty());
            fillerLabel.prefWidthProperty().bind(loginLabel.widthProperty());
            addTextListener(loginField);
            addTextListener(passwordField);
            loginButton.setOnAction(event -> {
                System.out.println("Try login");
            });
        }

        /**
         * Add text validator
         *
         * @param field textfield
         */
        private void addTextListener(TextField field) {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() < 5) {
                    field.setId("incorrect");
                } else {
                    field.setId("correct");
                }
            });
        }


    }

    /**
     * RegisterTab for registration user on server
     */
    private class RegisterPane extends Tab {
        private MaskInputView loginMaskView;
        private MaskInputView passwordMaskView;
        private MaskInputView confirmPasswordMaskView;
        private MaskInputView emailMaskview;
        private Button registerButton;

        public RegisterPane() {
            setText(resourceBundle.getString("register"));
            initComponents();
            initListeners();
        }

        private void initComponents() {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
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
            passwordMaskView = new MaskInputView(passwordField, new LengthValidator(5));
            passwordField.setPromptText(resourceBundle.getString("inputpwd"));
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
            emailMaskview = new MaskInputView(emailTextField, new EmailValidator());
            gridPane.add(emailLabel, 0, 3);
            gridPane.add(emailMaskview.getTextField(), 1, 3);
            CaptchaView captcha = new CaptchaView(100, 100);
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
                System.out.println("Register");
            });
        }


    }

}
