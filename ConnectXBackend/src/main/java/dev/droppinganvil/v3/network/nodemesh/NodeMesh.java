package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.network.CXNetwork;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class NodeMesh {
    public static ConcurrentHashMap<String, Integer> timeout = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> blacklist = new ConcurrentHashMap<>();
    public static PeerDirectory peers;
    public static InConnectionManager in;
    public static OutConnectionManager out;
    //Initial object must be signed by initiator
    //If it is a global resource encrypting for only the next recipient node is acceptable
    //If it is not a global resource it must be encrypted using only the end recipients key then re encrypted for transport
    public NodeMesh(File rootDir) {

    }
    public boolean connectNetwork(CXNetwork cxnet) {
        if (cxnet.)
    }
}
