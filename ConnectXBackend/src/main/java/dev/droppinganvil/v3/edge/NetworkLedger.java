/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkLedger implements Serializable {
    public String networkID;
    public Long chainID;
    public Long blockLength = 0L;
    public Map<Long, NetworkBlock> blockMap = new ConcurrentHashMap<>();

}
