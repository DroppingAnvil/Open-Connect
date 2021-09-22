/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectXAccount implements Serializable {
    public String id;
    public String email;
    public String name;
    public List<String> used_ips;
    public Map<String, Product> products;
    public String publicKey;
    public Map<Product, Long> subscriptions = new HashMap<>();
    public Boolean disabled = false;



}
