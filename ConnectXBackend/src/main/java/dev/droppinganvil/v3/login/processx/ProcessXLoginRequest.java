/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.login.processx;

import dev.droppinganvil.v3.Adapters;
import dev.droppinganvil.v3.LoginForm;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class ProcessXLoginRequest {
    public static String login(LoginForm lf, OkHttpClient httpClient) throws IOException {
        System.out.println(Adapters.loginFormjson.toJson(lf));
        Response response =
                httpClient.newCall(new Request.Builder()
                        .url("http://api2.twisted-palms.com/login:9075")
                        .post(RequestBody.create(null, Adapters.loginFormjson.toJson(lf)))
                        .build()
                ).execute();
        return response.body().string();
    }
}
