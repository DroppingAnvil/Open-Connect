package dev.droppinganvil.v3.network.nodemesh.events;

import dev.droppinganvil.v3.network.nodemesh.NetworkContainer;

import java.io.Serializable;

public class NetworkEvent extends NetworkContainer implements Serializable {
    public EventType eventType;
    /**
     * Event initializer
     */
    public String initiator;
    /**
     * Nodes that should process event
     */
    public String target;
    /**
     * Event specific data
     */
    public String data;
}
