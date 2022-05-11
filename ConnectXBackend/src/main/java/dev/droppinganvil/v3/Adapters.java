/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import dev.droppinganvil.v3.ProcessX.envconfs.EnvConf;
import dev.droppinganvil.v3.ProcessX.panel.Process;
import dev.droppinganvil.v3.ProcessX.panel.*;
import dev.droppinganvil.v3.edge.ConnectXAccount;

public class Adapters {
    public static Moshi moshi;
    public static JsonAdapter<LoginForm> loginFormjson;
    public static JsonAdapter<ConnectXAccount> clientjson;
    public static JsonAdapter<ProductServer> serverjson;
    public static JsonAdapter<ContainerList> modulelistrequestjson;
    public static JsonAdapter<EditUserRequest> editrequestjson;
    public static JsonAdapter<Process> processjson;
    public static JsonAdapter<ProcessList> processlistjson;
    public static JsonAdapter<EnvConf> envconfjson;

    public static void setupAdapters(Moshi moshi) {
        Adapters.moshi = moshi;
        loginFormjson = moshi.adapter(LoginForm.class).lenient();
        clientjson = moshi.adapter(ConnectXAccount.class).lenient();
        serverjson = moshi.adapter(ProductServer.class).lenient();
        modulelistrequestjson = moshi.adapter(ContainerList.class).lenient();
        editrequestjson = moshi.adapter(EditUserRequest.class);
        processjson = moshi.adapter(Process.class).lenient();
        processlistjson = moshi.adapter(ProcessList.class).lenient();
        envconfjson = moshi.adapter(EnvConf.class).lenient();
    }
}
