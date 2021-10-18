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
    public String data;
}
