/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.io;

import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.network.nodemesh.events.NetworkEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class NetworkEventObjectInputStream extends ObjectInputStream {
    public NetworkEventObjectInputStream(InputStream in) throws IOException {
        super(in);
    }
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc)
            throws IOException, ClassNotFoundException
    {
        if (!desc.getName().equals(NetworkEvent.class.getName())) {
            IOException e = new IOException();
            Analytics.addData(AnalyticData.Security_Event, e);
        }
        return NetworkEvent.class;
    }
}
