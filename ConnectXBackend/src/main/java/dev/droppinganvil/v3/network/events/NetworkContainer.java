/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.events;

import dev.droppinganvil.v3.network.CXPath;
import dev.droppinganvil.v3.network.nodemesh.TransmitPref;

import java.io.Serializable;

/**
 * This data should not be E2E it must be P2P to work effectively
 */
public class NetworkContainer implements Serializable {
    public byte[] e;

    public CXPath p;
    public String se = "cxJSON1";
    /**
     * Higher security mode - Not implemented, do not use
     */
    public boolean s = false;
    /**
     * Will be null if s = true
     */
    public String iD;
    public TransmitPref tP;
    public Double v;
    public String tID;

}
