/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.edge;

import dev.droppinganvil.v3.network.nodemesh.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This object is ONLY accessible on the client device itself due to encryption
 */
public class DataContainer implements Serializable {
    public String test = "test";
    /**
     * Stores nodes that have allowed direct connections
     */
    public Map<String, Node> LAN = new HashMap<>();
}
