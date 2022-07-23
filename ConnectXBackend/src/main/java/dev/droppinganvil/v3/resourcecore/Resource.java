/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.resourcecore;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.Permission;
import dev.droppinganvil.v3.State;
import dev.droppinganvil.v3.network.CXNetwork;
import java.io.Serializable;
import java.util.List;

public class Resource implements Serializable {
    /**
     * Resource ID
     * CXpath.
     */
    public String rID;
    public Long rV;
    public String oID;
    public ResourceType rt = null;
    public Availability a = null;
    /**
     * Location of resource on system if present
     */
    private String resourceLocation;
    public List<String> locations;
    public String hash;
    public Object o;
    public Object getObject(Class<?> clazz) {

    }

    public Resource publish(CXNetwork cxnet, ResourceType type, Availability availability, String hash, String resourceLocation, Object o) throws IllegalAccessException {
        assert locations == null;
        assert cxnet.c2 != null;
        assert cxnet.networkDictionary != null;
        assert cxnet.networkState == State.READY;
        switch (availability) {
            case PRIVATE_SYSTEM:
                if (!cxnet.configuration.unlimitedUpload) {
                    Integer w = cxnet.getVariableNetworkPermission(ConnectX.getOwnID(), Permission.AddResource.name());
                    if (w!=null && w!=0) {
                        if (ConnectX.locateResourceDIR(this).listFiles().length < w) {

                        }
                    }
                        throw new IllegalAccessException();
                        break;
                }
        }
    }
    //TODO resourceID validate
}
