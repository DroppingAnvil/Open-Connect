/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import java.io.*;
import java.net.ServerSocket;

public class InConnectionManager {
    public static ServerSocket serverSocket;

    public InConnectionManager(Integer i) throws IOException {
        serverSocket = new ServerSocket(i);
    }


}
