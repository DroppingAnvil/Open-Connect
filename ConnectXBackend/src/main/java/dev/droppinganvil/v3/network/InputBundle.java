/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network;

import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.events.NetworkEvent;

public class InputBundle {

    public InputBundle(NetworkEvent ne, NetworkContainer nc) {
        this.ne = ne;
        this.nc = nc;
    }

    public NetworkEvent ne;
    public NetworkContainer nc;
}
