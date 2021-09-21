/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.keychange;

import dev.droppinganvil.v2.Configuration;
import dev.droppinganvil.v2.EmbeddedAPI;
import dev.droppinganvil.v2.ServerKey;
import dev.droppinganvil.v2.Updater;
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
        ServerKey skr = EmbeddedAPI.serverKeyJsonAdapter.fromJson(response.body().string());
        EmbeddedAPI.serverKey = skr;
    }
}
