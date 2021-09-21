/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.keychange;

import dev.droppinganvil.v3.EmbeddedAPI;

public class MaintainKey implements Runnable{
    @Override
    public void run() {
        while(true) {
            if (EmbeddedAPI.serverKey.valid < System.currentTimeMillis() - 4000) {

            }
        }
    }
}
