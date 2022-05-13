package dev.droppinganvil.v3.network.nodemesh.events;


import dev.droppinganvil.v3.network.nodemesh.Node;

import java.io.Serializable;

public class NetworkEvent implements Serializable {

    public NetworkEvent(EventType type, byte[] data, Node destination) {
        this.eventType = type;
        this.data = data;

    }

    public EventType eventType;
    /**
     * Nodes that should process event. "NETWORK" targets all nodes
     */
    public String target = "NETWORK";
    public String networkID;
    /**
     * Event specific data
     */
    public byte[] data;
    /**
     * cxID of transmission sender. Must be set
     */
    public String NID = "UNKNOWN";
    /**
     * NID of network event initiator. Must be set
     */
    public String NEI = "UNKNOWN";
}
