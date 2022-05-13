/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.State;
import dev.droppinganvil.v3.edge.NetworkLedger;
import dev.droppinganvil.v3.network.NetworkDictionary;
import us.anvildevelopment.v1.util1.permissions.BasicPermissionContainer;

import java.util.Enumeration;

public class CXNetwork {
    public State networkState = State.ConnectNetworks;
    public Configuration configuration;
    public NetworkDictionary networkDictionary;
    public NetworkLedger c1;
    public NetworkLedger c2;
    public BasicPermissionContainer networkPermissions;

    public boolean checkChainPermission(String deviceID, String permission, Long chainID) {
        assert !permission.contains("-");
        assert !deviceID.contains("SYSTEM");
        String p = permission + "-"+chainID;
        return networkPermissions.allowed(deviceID, p);
    }

    public boolean checkNetworkPermission(String deviceID, String permission) {
        assert !permission.contains("-");
        assert !deviceID.contains("SYSTEM");
        return networkPermissions.allowed(deviceID, permission);
    }

    public boolean checkGlobalPermission(String deviceID, String permission) {
        return ConnectX.checkGlobalPermission(deviceID, permission);
    }

    public Integer getVariableNetworkPermission(String deviceID, String permission) {
        if (checkNetworkPermission(deviceID,permission)) {
            return networkPermissions.getEntryWeight(deviceID,permission);
        } else return null;
    }

    public CXNetwork() {}

}
