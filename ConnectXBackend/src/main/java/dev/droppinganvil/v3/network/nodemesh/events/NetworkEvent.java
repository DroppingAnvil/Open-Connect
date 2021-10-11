package dev.droppinganvil.v3.network.nodemesh.events;


import java.io.Serializable;

public class NetworkEvent implements Serializable {
    public EventType eventType;
    /**
     * Event initializer
     */
    public String initiator;
    /**
     * Nodes that should process event. "NETWORK" targets all nodes and is much more efficient
     */
    public String target = "NETWORK";
    /**
     * Event specific E2E data
     */
    public String data;
    /**
     * Only present once set by receiving socket
     */
    public String receiveAddress;
}
