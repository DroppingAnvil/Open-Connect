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
    public Long networkCreate;
    /**
     * When this object was last updated
     */
    public Long lastUpdate;
    /**
     * Base chain IDs
     */
    public Long c1;
    public Long c2;
    public Long c3;
    /**
     * Public keys of authorized backend nodes, in perspective of network master
     */
    public List<String> backendSet = new ArrayList<>();
    /**
     * Object to outline basic authentication
     */
    public BasicPermissionContainer networkPermissions;

}
