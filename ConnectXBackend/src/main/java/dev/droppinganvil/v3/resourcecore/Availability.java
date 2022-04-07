/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.resourcecore;

/**
 * Resource availability is focused on defining how resources should be handled and should not be used for authentication.
 */
public enum Availability {
    /**
     * Global Resource. Should be obtainable through any CX connected network
     */
    CX,
    /**
     * Global Resource. Should be obtainable through target CX Network.
     */
    NETWORK,
    /**
     * Private Resource. Should be obtainable through target CX network although custom permissions may apply.
     */
    PRIVATE_NETWORK,
    /**
     * Private Resource. Should be obtainable through any CX Network. Custom permissions will apply.
     */
    PRIVATE_CX,
    /**
     * Private Resource. Should only be obtainable by owner.
     */
    PRIVATE_OWNER,


}
