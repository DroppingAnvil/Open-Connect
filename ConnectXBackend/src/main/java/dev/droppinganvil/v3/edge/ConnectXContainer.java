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
 */
public class ConnectXContainer implements Serializable {
    /**
     * Should only be used for basic data flow. Authentication should never be done within this framework, it is based on the idea that only the device is able to read anything important so authentication to a resource lies in the ability to decrypt it
     */
    public String networkAccountID;
    /**
     * For future use, should be a JSON product package signed by NMI or NAS
     */
    public String productPackage;
    /**
     * Device's public key
     */
    public String publicKey;
    /**
     * Container for MessageX service
     */
    public MessageXContainer messageXContainer;
    /**
     * Users data container, used to store preferences and data only needed on client device.
     * Not accessible.
     */
    public byte[] dataContainer;

    /**
     * When a device joins the network it should generate its own key then upload its generated dataContainer
     * @param publicKey
     * @param dataContainer
     */
    public ConnectXContainer(String publicKey, String dataContainer) {

    }
}
