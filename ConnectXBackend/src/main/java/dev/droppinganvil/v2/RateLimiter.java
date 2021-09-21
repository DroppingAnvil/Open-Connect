/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2;

import dev.droppinganvil.v2.ProcessX.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter implements Runnable {
    public static boolean run = true;
    public static ConcurrentHashMap<String, Integer> request = new ConcurrentHashMap<>();
    @Override
    public void run() {
        while (run) {
            Logger.log("RateLimiter Started");
            for (Map.Entry<String, Integer> entry : request.entrySet()) {
                Integer i = entry.getValue();
                entry.setValue(i - 1);
            }

        }
    }
}
