/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.services.messagex;

import java.io.Serializable;
import java.util.List;

public class Chat implements Serializable {
    public String chatID;
    public List<Message> messages;
}
