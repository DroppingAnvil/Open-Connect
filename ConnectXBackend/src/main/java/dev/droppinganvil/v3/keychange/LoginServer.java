/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.keychange;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.ConnectXAPI;
import dev.droppinganvil.v3.Updater;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LoginServer {
    public static void login(ServerKey sk) throws IOException {
        Response response =
                Updater.client.newCall(new Request.Builder()
                        .url(Configuration.INTERNAL_CENTRAL_URL + "slogin")
                        .build()
                ).execute();
        ServerKey skr = ConnectXAPI.serverKeyJsonAdapter.fromJson(response.body().string());
        ConnectXAPI.serverKey = skr;
    }
}
