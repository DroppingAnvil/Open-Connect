/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh.threads;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.network.nodemesh.NodeMesh;

public class EventProcessor implements Runnable{
    public static boolean active = true;
    @Override
    public void run() {
        while (active) {
            NodeMesh.in.processEvent();
            try {
                Thread.sleep(Configuration.IO_THREAD_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
