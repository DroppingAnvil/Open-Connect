/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.exceptions.UnsafeKeywordException;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class PeerDirectory implements Serializable {
    public static ConcurrentHashMap<String,Node> peerCache;
    public static ConcurrentHashMap<String,Node> seen;
    public static ConcurrentHashMap<String, Node> lan;
    public static ConcurrentHashMap<String,Node> hv;
    public static File peers;


    //TODO
    //Writing node lookup and create account implementation, next up create peer finding event
    public static Node lookup(String cxID, boolean tryImport, boolean sync) throws UnsafeKeywordException {
        ConnectX.checkSafety(cxID);
        if (hv.containsKey(cxID)) return hv.get(cxID);
        if (seen.containsKey(cxID)) return seen.get(cxID);
        if (peerCache.contains(cxID)) return peerCache.get(cxID);
            char s = cxID.charAt(0);
            if (peers == null) peers = new File(ConnectX.cxRoot, "nodemesh");
            File peerGroup = new File(peers, String.valueOf(s));
            if (peerGroup.exists()) {
                File peer = new File(peerGroup, cxID+".cxi");
                if (peer.exists()) {
                    //if (sync) {
                        Node node = ConnectX.getSignedObject(cxID, peer.toURL().openStream(), Node.class, "cxJSON1");
                        peerCache.put(cxID, node);
                        ConnectX.encryptionProvider.cacheCert(cxID, false, false);
                        return node;
                   // } else {
                        //TODO async
                  //  }
                }
            } else if (tryImport & sync) {

            }

    }

    public static void addNode(Node n, byte[] signed) {
        if (Node.validate(n)) {

        } else {
            throw new IllegalStateException();
        }
    }

    public static boolean stableConnection() {
        return true;
    }
}
