package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.edge.ConnectXAccount;
import dev.droppinganvil.v3.network.CXNetwork;
import dev.droppinganvil.v3.network.InputBundle;
import dev.droppinganvil.v3.network.UnauthorizedNetworkConnectivityException;
import dev.droppinganvil.v3.network.events.EventType;
import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.events.NetworkEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NodeMesh {
    public static ServerSocket serverSocket;
    public static ConcurrentHashMap<String, Integer> timeout = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> blacklist = new ConcurrentHashMap<>();
    public static PeerDirectory peers;
    public static InConnectionManager in;
    public static ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(NodeConfig.pThreads);
    //Initial object must be signed by initiator
    //If it is a global resource encrypting for only the next recipient node is acceptable
    //If it is not a global resource it must be encrypted using only the end recipients key then re encrypted for transport
    public NodeMesh(ServerSocket serverSocket) {

    }
    public static void processNetworkInput(InputStream is, Socket socket) throws IOException, DecryptionFailureException, ClassNotFoundException, UnauthorizedNetworkConnectivityException {
        //TODO optimize streams
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //TODO max size
        NetworkContainer nc;
        NetworkEvent ne;
        ByteArrayOutputStream baoss = new ByteArrayOutputStream();
        ByteArrayInputStream bais;
        String networkEvent = "";

        Object o = ConnectX.encryptionProvider.decrypt(is, baos);
        String networkContainer = baos.toString("UTF-8");
        try {
            nc = (NetworkContainer) ConnectX.deserialize("cxJSON1", networkContainer, NetworkContainer.class);
            if (nc.cxID != null) ConnectX.checkSafety(nc.cxID);
            if (!ConnectX.isProviderPresent(nc.serialization)) {
                socket.close();
                Analytics.addData(AnalyticData.Tear, "Unsupported serialization method "+nc.serialization);
                return;
            }
            is.close();
            baos.close();
            bais = new ByteArrayInputStream(nc.event);
            Object o1;
            if (nc.s) {
                //TODO verification of full encrypt
                throw new DecryptionFailureException();
                //o1 = ConnectX.encryptionProvider.decrypt(bais, baoss);
            } else {
                o1 = ConnectX.encryptionProvider.verifyAndStrip(bais, baoss, nc.cxID);
            }
            networkEvent = baos.toString("UTF-8");
            ne = (NetworkEvent) ConnectX.deserialize(nc.serialization, networkEvent, NetworkEvent.class);
            bais.close();
            baoss.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (!NodeConfig.devMode) {
                if (NodeMesh.timeout.containsKey(socket.getInetAddress().getHostAddress())) {
                    NodeMesh.blacklist.put(socket.getInetAddress().getHostAddress(), "Protocol not respected");
                } else {
                    NodeMesh.timeout.put(socket.getInetAddress().getHostAddress(), 1000);
                }
            }
            socket.close();
            return;
        }
        synchronized (ConnectX.eventQueue) {
            ConnectX.eventQueue.add(new InputBundle(ne, nc));
        }
    }

    public static void processEvent() throws IOException, DecryptionFailureException {
        synchronized (ConnectX.eventQueue) {
            InputBundle ib = ConnectX.eventQueue.poll();
            EventType et = null;
            try {
                et = EventType.valueOf(ib.ne.eventType);
            } catch (Exception ignored) {}
            if (et == null & !ConnectX.sendPluginEvent(ib.ne, ib.ne.eventType)) {
                Analytics.addData(AnalyticData.Tear, "Unsupported event - "+ib.ne.eventType);
                if (NodeConfig.supportUnavailableServices) {
                    ConnectX.recordEvent(ib.ne);
                }
                return;
            }
            if (ib!=null) {
                //TODO non constant handling
                switch (et) {
                    case AccountCreate:
                        ConnectXAccount cxc = (ConnectXAccount) processObject(mapper, ne);

                }
            }
        }
    }
    public boolean connectNetwork(CXNetwork cxnet) {
        if (cxnet.)
    }
}
