/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network;

public class CXPath {
    public String scope;
    public String bridge;
    public String bridgeArg;
    public String network;
    public String address;
    public String cxID;
    public Integer version;
    public String resourceID;
    public Scope getScope() {
        return Scope.valueOf(scope);
    }
}
