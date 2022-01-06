/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge.jclient;

import dev.droppinganvil.v3.AnvilApp;
import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.utils.obj.RequiresLogin;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectXClient extends AnvilApp implements RequiresLogin {
    public ConnectX network = new ConnectX();

    public ConnectXClient() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @Override
    public void loginSuccess() throws IOException {

    }
}
