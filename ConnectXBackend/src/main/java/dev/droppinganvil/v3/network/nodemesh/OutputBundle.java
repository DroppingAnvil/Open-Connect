/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.events.NetworkEvent;

public class OutputBundle {
    public NetworkEvent ne;
    public Node n;
    public String s;
    public byte[] prev;
    public NetworkContainer nc;

    public OutputBundle(NetworkEvent ne, Node n, String s, byte[] prev, NetworkContainer nc) {
        this.ne = ne;
        this.n = n;
        this.s = s;
        this.prev = prev;
        this.nc = nc;
        if (this.nc==null) this.nc = new NetworkContainer();
    }
}
