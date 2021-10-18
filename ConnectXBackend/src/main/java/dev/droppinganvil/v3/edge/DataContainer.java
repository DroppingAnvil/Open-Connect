/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import dev.droppinganvil.v3.network.nodemesh.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This object is ONLY accessible on the client device itself due to encryption
 */
public class DataContainer implements Serializable {
    public String test = "test";
    /**
     * For future use to share data between devices
     */
    public List<Node> otherDevices = new ArrayList<>();
}
