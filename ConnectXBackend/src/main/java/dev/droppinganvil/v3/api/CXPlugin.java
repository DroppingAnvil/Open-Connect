/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.api;

import dev.droppinganvil.v3.network.events.NetworkEvent;

public abstract class CXPlugin {
    public String serviceName;
    public CXPlugin(String serviceName) {
        this.serviceName = serviceName;
    }
    public boolean handleEvent(NetworkEvent ne) {return true;}
}
