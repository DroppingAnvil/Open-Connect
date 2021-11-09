package dev.droppinganvil.v3.network.nodemesh.events;


import java.io.Serializable;

public class NetworkEvent implements Serializable {
    public EventType eventType;
    /**
     * Nodes that should process event. "NETWORK" targets all nodes and is much more efficient
     */
    public String target = "NETWORK";
    /**
     * Event specific data
     */
    public byte[] data;
    /**
     * Network ID of transmission sender. Must be set
     */
    public String NID = "UNKNOWN";
    /**
     * NID of network event initiator. Must be set
     */
    public String NEI = "UNKNOWN";
}
