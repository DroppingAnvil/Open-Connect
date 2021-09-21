/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.login;

import dev.droppinganvil.v2.Adapters;
import dev.droppinganvil.v2.LoginForm;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class LoginRequest {
    public static String login(LoginForm lf, OkHttpClient httpClient) throws IOException {
        Response response =
                httpClient.newCall(new Request.Builder()
                        .url("https://192.168.254.151:9050/login")
                        .post(RequestBody.create(null, Adapters.loginFormjson.toJson(lf)))
                        .build()
                ).execute();
        return response.body().string();
    }
}
