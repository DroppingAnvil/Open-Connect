/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh.bridge.http;

import dev.droppinganvil.v3.network.nodemesh.OutputBundle;
import dev.droppinganvil.v3.network.nodemesh.bridge.CXBridge;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.net.Socket;

/**
 * Simple HTTP bridge for CX.
 * CXPath:
 *  Bridge: cxHTTP
 *  BridgeArg: HTTP Path
 */
public class HTTPBridge implements CXBridge {
    public static OkHttpClient httpClient;

    @Override
    public String getProtocol() {
        return "cxHTTP";
    }

    @Override
    public Integer getVersion() {
        return 1;
    }

    @Override
    public void setup() {
        httpClient = new OkHttpClient();
    }

    @Override
    public Socket connect(OutputBundle ob) {
        return null;
    }

    @Override
    public boolean getDirectSocket() {
        return false;
    }

    @Override
    public boolean transmitEvent(OutputBundle ob, byte[] data) {

        Request r = new Request.Builder().u
        return false;
    }
}
