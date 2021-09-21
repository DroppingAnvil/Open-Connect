package dev.droppinganvil.v2.login;

import dev.droppinganvil.v2.AnvilApp;
import dev.droppinganvil.v2.RequiresLogin;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class FireLoginPane extends AnvilApp implements RequiresLogin {
    public static void main(String... args) {
        launch(args);
    }

    public void loginSuccess() throws IOException {

    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        AnvilApp.primaryStage = primaryStage;
        primaryStage.setTitle("IPX - Development Copy");
        Login.httpClient = new OkHttpClient();
        Login.loadLoginPane(primaryStage, this);
    }
}
