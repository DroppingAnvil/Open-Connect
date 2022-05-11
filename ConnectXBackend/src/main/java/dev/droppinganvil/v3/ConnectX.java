/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.pgpainless.PainlessCryptProvider;
import dev.droppinganvil.v3.io.IOJob;
import dev.droppinganvil.v3.network.nodemesh.Node;
import dev.droppinganvil.v3.resourcecore.Availability;
import dev.droppinganvil.v3.resourcecore.Resource;
import dev.droppinganvil.v3.resourcecore.ResourceType;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectX {
    public static Platform platform;
    public CryptProvider encryptionProvider = new PainlessCryptProvider();
    public final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public File cxRoot = new File("ConnectX");
    public Node self;


    public ConnectX() throws IOException {
        String osS = System.getProperty("os.name");
        osS = osS.toLowerCase(Locale.ROOT);
        if (osS.contains("linux")) {
            platform = Platform.LINUX_GENERIC;
        } else if (osS.contains("windows")) {
            platform = Platform.WINDOWS;
        } else if (osS.contains("osx")) {
            platform = Platform.OSX;
        } else if (osS.contains("connectx")) {
            platform = Platform.ConnectX;
        }
        if (platform == null) platform = Platform.Unknown;
        if (!cxRoot.exists()) {
            if (!cxRoot.mkdir()) throw new IOException();
            //TODO network join 
        }
    }

    public void connectNetwork(){}

    public Resource locateResource(String networkID, ResourceType type, Availability availability) {

    }


    /**
     * Network interfaces
     */

    public void transmitMessage(String message, Node node) {

    }

}
