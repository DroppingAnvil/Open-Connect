/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import dev.droppinganvil.v3.network.services.messagex.MessageXContainer;

import java.io.Serializable;

/**
 * Account for any device on the ConnectX network.
 * All fields other than dataContainer are accessible from network layer
 * Data should be signed by owner (public key should match signed public key)
 */
public class ConnectXAccount implements Serializable {
    /**
     * Should only be used for basic data flow. Authentication should never be done within this framework, it is based on the idea that only the device is able to read anything important so authentication to a resource lies in the ability to decrypt it
     */
    public String networkAccountID;
    /**
     * Device's public key
     */
    public String publicKey;
    /**
     * For future use.
     * Not accessible.
     */
    public byte[] dataContainer;

    /**
     * When a device joins the network it should generate it's own key then upload generated ConnectXNode
     * @param publicKey
     */
    public ConnectXAccount(String networkAccountID) {

    }
}
