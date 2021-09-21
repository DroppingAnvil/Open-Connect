/*
 * Copyright (c) 2021 Christopher (DroppingAnvil) Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.login;

import dev.droppinganvil.v2.AnvilApp;
import dev.droppinganvil.v2.LoginForm;
import dev.droppinganvil.v2.RequiresLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import okhttp3.OkHttpClient;

import java.io.File;
import java.io.IOException;

public class Login {
    public static Login instance;
    public static OkHttpClient httpClient;
    public TextField email_entry;
    public PasswordField password_entry;
    public Button continue_button;
    public Label login_response;

    public static FXMLLoader getLoader() {
        return new FXMLLoader(Login.class.getResource("Login.fxml"));
    }
    public static FXMLLoader getTestLoader() {
        try {
            return new FXMLLoader(new File("/Users/droppinganvil/Desktop/DADSDK/src/main/resources/Login.fxml").toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadLoginPane(Stage primaryStage, RequiresLogin app) throws IOException {
        FXMLLoader fxmlLoader = getTestLoader();
        Parent root = fxmlLoader.load();
        instance = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root, 500, 220));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        instance.continue_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                loginForm.id = instance.email_entry.getText();
                loginForm.auth = instance.password_entry.getText();
                String response;
                try {
                    response = LoginRequest.login(loginForm, httpClient);
                } catch (IOException ioException) {
                    response = "Error during login, Please try again later";
                    ioException.printStackTrace();
                }
                if (!response.contains(" ")) {
                    AnvilApp.client.key = response;
                } else {
                    instance.login_response.setText(response);
                }
                try {
                    app.loginSuccess();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
