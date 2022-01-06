/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import dev.droppinganvil.v3.network.nodemesh.Node;
import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;
import us.anvildevelopment.v1.util1.database.annotations.MemoryOnly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkLedger implements Serializable {
    /**
     * Stores all nodes that have been found
     */
    public Map<String, Node> WAN = new HashMap<>();
    public Map<Long, NetworkEvent> networkEvents = new HashMap<>();
}
