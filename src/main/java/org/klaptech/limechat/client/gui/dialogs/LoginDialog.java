package org.klaptech.limechat.client.gui.dialogs;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ResourceBundle;

/**
 * Login/Register dialog
 * Contains: login field, password field, default room selector, remember password check.
 * Register and login mode can be switched by using tabs
 *
 * @author rlapin
 */
public class LoginDialog {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private Stage stage;
    private ResourceBundle resourceBundle;

    public LoginDialog() {
        stage = new Stage(StageStyle.DECORATED);
        resourceBundle = ResourceBundle.getBundle("org.klaptech.limechat.client.gui.dialogs.LoginDialog");

        initComponents();
        initListeners();
    }

    private void initListeners() {

    }


    private void initComponents() {
        TabPane loginTabPane = new TabPane(new LoginPane(), new RegisterPane());
        loginTabPane.setSide(Side.BOTTOM);
        loginTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/login.css").toExternalForm());
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
        private TextField loginField;
        private PasswordField passwordField;

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
            Label titleLabel = new Label("Login");
            titleLabel.setFont(new Font(72));
            titleLabel.setEffect(new InnerShadow(10,Color.WHITE));
            gridPane.add(titleLabel,0,0,2,1);
            Label loginLabel = new Label(resourceBundle.getString("name"));
            gridPane.add(loginLabel, 0, 1);
            loginField = new TextField();
            loginField.setPromptText(resourceBundle.getString("inputlogin"));
            gridPane.add(loginField, 1, 1);
            Label passwordLabel = new Label(resourceBundle.getString("password"));
            gridPane.add(passwordLabel, 0, 2);
            passwordField = new PasswordField();
            passwordField.setPromptText(resourceBundle.getString("inputpwd"));
            gridPane.add(passwordField, 1, 2);
            setContent(gridPane);
        }

        private void initListeners() {

        }
    }

    /**
     * RegisterTab for registration user on server
     */
    private class RegisterPane extends Tab {
        private TextField loginField;
        private PasswordField passwordField;
        private PasswordField confirmPasswordField;

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
            loginField = new TextField();
            loginField.setPromptText(resourceBundle.getString("inputlogin"));
            gridPane.add(loginField, 1, 0);
            Label passwordLabel = new Label(resourceBundle.getString("password"));
            gridPane.add(passwordLabel, 0, 1);
            passwordField = new PasswordField();
            passwordField.setPromptText(resourceBundle.getString("inputpwd"));
            gridPane.add(passwordField, 1, 1);
            Label confirmPasswordLabel = new Label(resourceBundle.getString("confirmpwd"));
            gridPane.add(confirmPasswordLabel, 0, 2);
            confirmPasswordField = new PasswordField();
            confirmPasswordField.setPromptText(resourceBundle.getString("repeatpwd"));
            gridPane.add(confirmPasswordField, 1, 2);
            setContent(gridPane);
        }

        private void initListeners() {

        }
    }
}
