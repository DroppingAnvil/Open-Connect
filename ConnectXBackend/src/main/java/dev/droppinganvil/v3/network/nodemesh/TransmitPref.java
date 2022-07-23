/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

public class TransmitPref {
    public boolean directOnly = false;
    public boolean peerProxy = false;
    public boolean peerBroad = true;
    public String proxy = null;

    public TransmitPref() {

    }
}
