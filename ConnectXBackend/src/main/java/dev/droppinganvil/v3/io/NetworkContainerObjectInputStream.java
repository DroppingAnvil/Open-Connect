/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.io;

import dev.droppinganvil.v3.network.nodemesh.NetworkContainer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class NetworkContainerObjectInputStream extends ObjectInputStream {
    private InputStream is;
    public NetworkContainerObjectInputStream(InputStream in) throws IOException {
        super(in);
        is = in;
    }
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc)
            throws IOException, ClassNotFoundException
    {
        if (!desc.getName().equals(NetworkContainer.class.getName())) {
            System.out.println("Potential attack detected!");
        }
        return NetworkContainer.class;
    }
}
