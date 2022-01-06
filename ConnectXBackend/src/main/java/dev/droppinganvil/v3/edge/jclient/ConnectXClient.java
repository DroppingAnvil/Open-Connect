/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge.jclient;

import dev.droppinganvil.v3.AnvilApp;
import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.utils.obj.RequiresLogin;
import javafx.stage.Stage;

import java.io.File;
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
    public boolean attemptLogin(String id, String auth) throws IOException {
        File dir = new File(id);
        if (!dir.exists()) return false;
        try {
            network.encryptionProvider.setup(id, auth, network.cxRoot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
