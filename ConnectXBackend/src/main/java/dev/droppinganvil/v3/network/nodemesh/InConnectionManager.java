/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.edge.ConnectXContainer;
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

    public void processEvent(ObjectMapper mapper) throws IOException, DecryptionFailureException {
        synchronized (eventQueue) {
            NetworkEvent ne = eventQueue.poll();
            if (ne!=null) {
                switch (ne.eventType) {
                    case ACCOUNT_CREATE:
                        ConnectXContainer cxc = (ConnectXContainer) processObject(mapper, ne);

                }
            }
        }
    }
    public static Object processObject(ObjectMapper mapper, NetworkEvent ne) throws JsonProcessingException, DecryptionFailureException, UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CryptServiceProvider.encryptionProvider.decryptNetworked(new ByteArrayInputStream(ne.data), baos, ne.NEI);
        String json = baos.toString("UTF-8");
        switch (ne.eventType) {
            case ACCOUNT_CREATE:
                return mapper.readValue(json, ConnectXContainer.class);
        }
        return null;
    }
    public static byte[] processJSON(ObjectMapper mapper, Object o) throws JsonProcessingException {
        return mapper.writeValueAsBytes(o);
    }
}
