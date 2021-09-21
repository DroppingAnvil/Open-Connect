/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.serverauth;

import dev.droppinganvil.v3.keychange.ServerKey;

import java.util.Map;
import java.util.UUID;

public class ServerKeyManager implements Runnable {
    @Override
    public void run() {
        while (true) {
            for (Map.Entry<String, ServerKey> entry : ServerKeys.keyCache.entrySet()) {
                ServerKey sk = entry.getValue();
                if (sk.valid > System.currentTimeMillis()) {
                    String newUUID = UUID.randomUUID().toString();
                    ServerKeys.tempKeys.remove(sk.tempKey);
                    ServerKeys.tempKeys.add(newUUID);
                    sk.tempKey = newUUID;
                }
            }
        }
    }
}
