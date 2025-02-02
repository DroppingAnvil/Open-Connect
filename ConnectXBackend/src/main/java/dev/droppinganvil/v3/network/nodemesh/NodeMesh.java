package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.network.CXNetwork;
import dev.droppinganvil.v3.network.InputBundle;
import dev.droppinganvil.v3.network.UnauthorizedNetworkConnectivityException;
import dev.droppinganvil.v3.network.events.EventType;
import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.events.NetworkEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NodeMesh {
    public static ServerSocket serverSocket;
    public static ConcurrentHashMap<String, ArrayList<String>> transmissionIDMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> timeout = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> blacklist = new ConcurrentHashMap<>();
    public static PeerDirectory peers;
    public static InConnectionManager in;
    public static Connections connections = new Connections();
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
            if (nc.iD != null) ConnectX.checkSafety(nc.iD);
            assert nc.v != null;
            if (!ConnectX.isProviderPresent(nc.se)) {
                socket.close();
                Analytics.addData(AnalyticData.Tear, "Unsupported serialization method "+nc.se);
                return;
            }
            is.close();
            baos.close();
            bais = new ByteArrayInputStream(nc.e);
            Object o1;
            if (nc.s) {
                //TODO verification of full encrypt
                throw new DecryptionFailureException();
                //o1 = ConnectX.encryptionProvider.decrypt(bais, baoss);
            } else {
                o1 = ConnectX.encryptionProvider.verifyAndStrip(bais, baoss, nc.iD);
            }
            networkEvent = baos.toString("UTF-8");
            ne = (NetworkEvent) ConnectX.deserialize(nc.se, networkEvent, NetworkEvent.class);
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
                et = EventType.valueOf(ib.ne.eT);
            } catch (Exception ignored) {}
            if (et == null & !ConnectX.sendPluginEvent(ib.ne, ib.ne.eT)) {
                Analytics.addData(AnalyticData.Tear, "Unsupported event - "+ib.ne.eT);
                if (NodeConfig.supportUnavailableServices) {
                    ConnectX.recordEvent(ib.ne);
                    //todo relay
                }
                return;
            }
            if (ib!=null) {
                //TODO non constant handling
                switch (et) {
                    case NewNode:
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ByteArrayInputStream bais = new ByteArrayInputStream(ib.nc.e);
                        Object o = ConnectX.encryptionProvider.decrypt(bais, baos);
                        Node node = ConnectX.deserialize("cxJSON1", baos.toString("UTF-8"), Node.class);
                        Node node1 = PeerDirectory.lookup(node.cxID, true, true);
                        if (node1 != null) {
                            ConnectX.encryptionProvider.cacheCert(node1.cxID, true, false);
                            return;
                        }
                        PeerDirectory.addNode(node);
                        break;
                }
            }
        }
    }
    public static boolean fireEvent(NetworkEvent ne) {
        if (ne.target.equalsIgnoreCase("CXN")) {
            for (Node n : PeerDirectory.hv.values()) {
                if
            }
        }
        if (ne.eT.equalsIgnoreCase(EventType.PeerFinding.name())) {

        }
    }
    public boolean connectNetwork(CXNetwork cxnet) {
        if (cxnet.)
    }
}
