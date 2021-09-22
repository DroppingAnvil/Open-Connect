package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;

import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NodeMesh {
    public static final ConcurrentHashMap<String, Node> nodeMap = new ConcurrentHashMap<>();
    //Initial object must be signed by initiator
    //If it is a global resource encrypting for only the next recipient node is acceptable
    //If it is not a global resource it must be encrypted using only the end recipients key then re encrypted for transport
    public NodeMesh(InetSocketAddress isa) {

    }
}
