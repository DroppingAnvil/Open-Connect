/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.edge.ConnectXAccount;
import dev.droppinganvil.v3.network.events.NetworkEvent;

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
                //TODO non constant handling
                switch (ne.eventType) {
                    case AccountCreate:
                        ConnectXAccount cxc = (ConnectXAccount) processObject(mapper, ne);

                }
            }
        }
    }
    public static Object processObject(ObjectMapper mapper, NetworkEvent ne) throws JsonProcessingException, DecryptionFailureException, UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ConnectX.checkSafety(ne.);
        ConnectX.encryptionProvider.decrypt(new ByteArrayInputStream(ne.data), baos, ne.NEI);
        String json = baos.toString("UTF-8");
        switch (ne.eventType) {
            case AccountCreate:
                return mapper.readValue(json, ConnectXAccount.class);
        }
        return null;
    }
    public static byte[] processJSON(ObjectMapper mapper, Object o) throws JsonProcessingException {
        return mapper.writeValueAsBytes(o);
    }
}
