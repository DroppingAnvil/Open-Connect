/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import java.io.Serializable;

public class ConnectXContainer implements Serializable {
    public String productPackage;
    public String publicKey;
    public String dataContainer;

    /**
     * When a device joins the network it should generate its own key then upload its generated dataContainer
     * @param publicKey
     * @param dataContainer
     */
    public ConnectXContainer(String publicKey, String dataContainer) {

    }
}
