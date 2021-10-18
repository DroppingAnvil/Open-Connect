/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.crypt.core.CryptServiceProvider;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InConnectionManager {
    public static ServerSocket serverSocket;
    public ArrayList<String> blacklistedConnections = new ArrayList<>();
    public final Queue<NetworkEvent> eventQueue = new ConcurrentLinkedQueue<>();

    public InConnectionManager(Integer i) throws IOException {
        serverSocket = new ServerSocket(i);
    }

    public void processEvent() throws IOException, DecryptionFailureException {
        synchronized (eventQueue) {
            NetworkEvent ne = eventQueue.poll();
            if (ne!=null) {
                switch (ne.eventType) {
                    case ACCOUNT_CREATE:
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        CryptServiceProvider.encryptionProvider.decrypt(new ByteArrayInputStream(ne.data.getBytes()), baos);
                        baos.toString()
                }
            }
        }
    }
}
