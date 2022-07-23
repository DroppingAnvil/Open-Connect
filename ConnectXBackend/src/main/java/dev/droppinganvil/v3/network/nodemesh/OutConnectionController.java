package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.network.CXNetwork;
import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.events.NetworkEvent;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class OutConnectionController {
    public ConnectX connectXAPI;

    public OutConnectionController(ConnectX api) {
        connectXAPI = api;
    }

    public void transmitEvent(OutputBundle out) throws Exception {
        //TODO streams
        NetworkContainer nc = out.nc;
        byte[] cryptEvent;
        cryptEvent = out.prev;
        if (cryptEvent == null) {
            if (nc.s) {
                //E2E Event Layer
                assert out.n != null;
                //TODO
            } else {
                nc.cxID = ConnectX.getOwnID();
                if (NodeConfig.revealVersion) nc.cxV = NodeConfig.cxV;
                cryptEvent = ConnectX.signObject(out.ne, NetworkEvent.class, nc.serialization).toByteArray();
            }
        }
        nc.event = cryptEvent;
        byte[] cryptNetworkContainer = ConnectX.signObject(nc, NetworkContainer.class, nc.serialization).toByteArray();
        if (out.ne.target.equalsIgnoreCase("CXNET")) {
            for (Node n : PeerDirectory.hv.values()) {
                try {
                    if (n.addr != null) {
                        String[] addr = n.addr.split(":");
                        Socket s = new Socket(addr[0], Integer.parseInt(addr[1]));
                        s.getOutputStream().write(cryptNetworkContainer);
                        s.close();
                    }
                    if (n.path != null) {
                        //TODO
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } else {
            String[] path = out.ne.target.split(":");
            if (path[0].equalsIgnoreCase("CXI")) {
                Node n;
                if (PeerDirectory.lan.containsKey(path[1])) {
                    n = PeerDirectory.lan.get(path[1]);
                } else {
                    n = PeerDirectory.lookup(path[1], true, true);
                }
                String[] addr = n.addr.split(":");
                Socket s = new Socket(addr[0], Integer.parseInt(addr[1]));
                s.getOutputStream().write(cryptNetworkContainer);
                s.close();
                return;
            }
            if (path[0].equalsIgnoreCase("CXN")) {
                CXNetwork cxn = ConnectX.getNetwork(path[1]);
                if (cxn != null) {
                    for (String s : cxn.configuration.backendSet) {
                        Node n = PeerDirectory.lookup(s, true, true);
                        String[] addr = n.addr.split(":");
                        Socket so = new Socket(addr[0], Integer.parseInt(addr[1]));
                        so.getOutputStream().write(cryptNetworkContainer);
                        so.close();
                    }
                }
            }
        }


    }

}
