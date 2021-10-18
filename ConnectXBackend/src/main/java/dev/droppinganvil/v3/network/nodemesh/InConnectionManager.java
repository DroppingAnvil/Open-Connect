/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InConnectionManager {
    public static ServerSocket serverSocket;
    public static final Queue<NetworkEvent> eventQueue = new ConcurrentLinkedQueue<>();

    public InConnectionManager(Integer i) throws IOException {
        serverSocket = new ServerSocket(i);
    }

    public static void processEvent() {
        synchronized (eventQueue) {
            NetworkEvent ne = eventQueue.poll();
            if (ne!=null) {
                switch (ne.eventType) {

                }
            }
        }
    }
}
