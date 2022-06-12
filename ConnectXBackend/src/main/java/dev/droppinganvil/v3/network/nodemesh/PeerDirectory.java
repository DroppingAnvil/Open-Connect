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
    /**
     * String cxID, String resourceID
     */
    public static ConcurrentHashMap<String,String> allPeers;
    public static ConcurrentHashMap<String,Node> seen;
    public static ConcurrentHashMap<String,Node> hv;
    public static File peers;

    public static Node lookup(String cxID, boolean tryImport, boolean sync) throws UnsafeKeywordException {
        ConnectX.checkSafety(cxID);
        if (hv.containsKey(cxID)) return hv.get(cxID);
        if (seen.containsKey(cxID)) return seen.get(cxID);
        if (allPeers.containsKey(cxID)) {
            char s = cxID.charAt(0);
            if (peers == null) peers = new File(ConnectX.cxRoot, "peers");
            File peerGroup = new File(peers, String.valueOf(s));
            if (peerGroup.exists()) {
                File peer = new File(peerGroup, cxID);
                if (peer.exists()) {

                }
            } else {

            }
        }

    }
}
