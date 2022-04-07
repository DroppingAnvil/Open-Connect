/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;
import us.anvildevelopment.v1.util1.database.annotations.MemoryOnly;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkLedger implements Serializable {
    public String networkID;
    public Long chainID;
    public Long blockLength = 0L;
    public boolean lock = false;
    @MemoryOnly
    public NetworkBlock current;
    /**
     * Long Key is the block ID only unique inside it's chain
     */
    public Map<Long, NetworkBlock> blockMap = new ConcurrentHashMap<>();


    public NetworkLedger(String networkID, Long chainID, Long blockLength) {

    }



}
