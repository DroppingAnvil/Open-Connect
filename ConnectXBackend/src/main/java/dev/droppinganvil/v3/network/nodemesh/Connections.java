/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;
import java.util.HashSet;

public class Connections implements Serializable {
    public HashSet<String> hv;
    public HashSet<String> peers;
    public HashSet<String>  all;

    public Connections() {}
}
