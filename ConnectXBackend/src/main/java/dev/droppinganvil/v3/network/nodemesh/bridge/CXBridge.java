/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh.bridge;

import dev.droppinganvil.v3.network.nodemesh.OutputBundle;

import java.net.Socket;

public interface CXBridge {
    String getProtocol();
    Integer getVersion();
    void setup();
    Socket connect(OutputBundle ob);
    boolean getDirectSocket();
    boolean transmitEvent(OutputBundle ob, byte[] data);
}
