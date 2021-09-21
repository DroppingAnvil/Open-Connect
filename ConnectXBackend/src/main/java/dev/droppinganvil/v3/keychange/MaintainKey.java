/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.keychange;

import dev.droppinganvil.v3.ConnectXAPI;

public class MaintainKey implements Runnable{
    @Override
    public void run() {
        while(true) {
            if (ConnectXAPI.serverKey.valid < System.currentTimeMillis() - 4000) {

            }
        }
    }
}
