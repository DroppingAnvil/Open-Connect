/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.resourcecore;

import dev.droppinganvil.v3.State;
import dev.droppinganvil.v3.network.nodemesh.CXNetwork;

public class Resource {
    public ResourceType rt = null;
    public Availability a = null;
    public String resourceID;
    public String resourceLocation;
    public String hash;
    public Resource publish(CXNetwork cxnet, ResourceType type, Availability availability, String hash, String resourceLocation, Object o) {
        assert cxnet.c2 != null;
        assert cxnet.networkDictionary != null;
        assert cxnet.networkState == State.READY;
        if (cxnet.configuration.)
    }
}
