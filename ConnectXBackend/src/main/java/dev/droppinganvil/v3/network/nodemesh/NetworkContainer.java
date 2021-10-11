package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.network.nodemesh.events.EventType;

import java.io.Serializable;

/**
 * This data should not be E2E it must be P2P to work effectively
 */
public class NetworkContainer implements Serializable {
    public String signedEvent;
    public EventType et;

}
