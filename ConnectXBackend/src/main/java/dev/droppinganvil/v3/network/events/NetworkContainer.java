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
    public String signedEvent;
    public String serialization;
    public String cxID;

}
