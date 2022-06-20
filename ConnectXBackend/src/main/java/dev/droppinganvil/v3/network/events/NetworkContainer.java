/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.events;

import java.io.Serializable;

/**
 * This data should not be E2E it must be P2P to work effectively
 */
public class NetworkContainer implements Serializable {
    public byte[] event;
    public String serialization = "cxJSON1";
    /**
     * Higher security mode - Not implemented, do not use
     */
    public boolean s = false;
    /**
     * Will be null if s = true
     */
    public String cxID;

}
