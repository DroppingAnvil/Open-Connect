/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import java.util.concurrent.ConcurrentHashMap;

public class ClientData {
    public static ConcurrentHashMap<String, ConnectXContainer> clientCache = new ConcurrentHashMap<>();

}
