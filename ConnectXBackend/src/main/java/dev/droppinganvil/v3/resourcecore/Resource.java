/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.resourcecore;

import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.Permission;
import dev.droppinganvil.v3.State;
import dev.droppinganvil.v3.network.CXNetwork;
import dev.droppinganvil.v3.network.CXPath;
import dev.droppinganvil.v3.network.nodemesh.Node;
import dev.droppinganvil.v3.network.nodemesh.PeerDirectory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Resource implements Serializable {
    public CXPath p;
    /**
     * Resource version
     */
    public Long rV;
    /**
     * Resource owner ID
     */
    public String oID;
    public ResourceType rt = null;
    public Availability a = null;
    /**
     * for later implementation
     */
    private String rL;
    public List<CXPath> l;
    public String hash;
    /**
     * Should reflect if the resource is a reference to the data or presently contains the data
     * true reflects a reference
     * false reflects data carrier
     */
    public boolean ref = true;
    private Object o;
    /**
     * Method used for persistence
     */
    public String m;
    /**
     * Original
     */
    public byte[] or;
    /**
     * Resource data if contained
     */
    public byte[] rD;
    public Object getObject(Class<?> clazz, Boolean sync, boolean tryImports) {
        if (o != null) return o;
        Node owner = PeerDirectory.lookup(oID, tryImports, true);
        if (rD != null & !ref) {
            ByteArrayInputStream bais = new ByteArrayInputStream(rD);
            o = ConnectX.getSignedObject(oID, bais, clazz, m);
            return o;
        }
        File f = null;
        //TODO futureproof
        switch (p.getScope()) {
            case CXN: f = new File(ConnectX.resources, p.network);
            break;
            case CXS: f = new File(ConnectX.resources, p.cxID);
            break;
        }
        if (f!=null & !f.exists()) f.mkdir();
        File resource = new File(f, p.resourceID);
        if (resource.exists()) {
            if (ref) {
                File signedObject = new File(resource, "cobj.cx");
                if (signedObject.exists()) {
                    o = ConnectX.getSignedObject(oID, signedObject.toURL().openStream(), clazz, "cxJSON1");
                    return o;
                } else if (tryImports) {

                }
            } else {
                return null;
            }
        }

    }

    public static void importResource(Resource r) {
        File
    }

    public Resource publish(CXNetwork cxnet, ResourceType type, Availability availability, String hash, String resourceLocation, Object o) throws IllegalAccessException {
        assert l == null;
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
