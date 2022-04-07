/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.resourcecore;

public enum RefreshIntention {
    /**
     * Changes will be applied to the object refresh is called on (Can be dangerous)
     */
    UpdateCurrentObject,
    /**
     * Changes will be applied to content on the file system if a data resource is present
     */
    UpdateFS,
}
