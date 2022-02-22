/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network;

import us.anvildevelopment.v1.util1.permissions.BasicPermissionContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NetworkDictionary implements Serializable {
    /**
     * Network Master Identity public key
     */
    public String nmi;
    public String networkID;
    /**
     * Public keys of authorized backend nodes
     */
    public List<String> backendSet = new ArrayList<>();
    /**
     * Object to outline authentication
     */
    public BasicPermissionContainer networkPermissions;

}
