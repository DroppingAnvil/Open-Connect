package dev.droppinganvil.v3.network.events;


import dev.droppinganvil.v3.network.nodemesh.Node;

import java.io.Serializable;

public class NetworkEvent implements Serializable {

    public NetworkEvent(EventType type, byte[] data) {
        this.eventType = type.name();
        this.data = data;

    }

    public String eventType;
    public
    public String cxID;
    /**
     * Method for processing
     */
    public String m;
    /**
     * Event specific data
     */
    public byte[] data;
}
