/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.login;

import dev.droppinganvil.v3.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class LoginRequest {
    public static String login(LoginForm lf, OkHttpClient httpClient) throws IOException {
        Response response =
                httpClient.newCall(new Request.Builder()
                        .url("https://"+Configuration.control+"."+Configuration.domain +"/api/v2/login")
                        .post(RequestBody.create(null, Adapters.loginFormjson.toJson(lf)))
                        .build()
                ).execute();
        return response.body().string();
    }
}
