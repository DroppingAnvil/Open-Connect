/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.State;
import dev.droppinganvil.v3.edge.NetworkLedger;
import dev.droppinganvil.v3.network.NetworkDictionary;

public class CXNetwork {
    public State networkState = State.ConnectNetworks;
    public Configuration configuration;
    public NetworkDictionary networkDictionary;
    public NetworkLedger c1;
    public NetworkLedger c2;

    public CXNetwork() {}

}
