/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.pgpainless.PainlessCryptProvider;
import dev.droppinganvil.v3.io.IOJob;
import dev.droppinganvil.v3.network.nodemesh.CXNetwork;
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
    public State state = State.CXConnecting;
    public CryptProvider encryptionProvider = new PainlessCryptProvider();
    public final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public static File cxRoot = new File("ConnectX");
    private transient static CXNetwork cx;
    private static transient Node self;


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

    public static String getOwnID() {
        return self.cxID;
    }
    public static String getOwnPublicKey() {
        return self.publicKey;
    }

    public void connect() {}

    public static boolean checkGlobalPermission(String deviceID, String permission) {
        assert cx != null;
        assert !deviceID.contains("SYSTEM");
        return cx.checkNetworkPermission(deviceID, permission);
    }

    public static File locateResourceDIR(Resource resource) {
        //TESTNET0.cxID.rrrrrrrrrrrrrrrrrrrrrrrr 25
        String[] spl = resource.resourceID.split("\\.");
        File network = new File(cxRoot, spl[0]);
        if (!network.exists()) return null;
        File f = new File(network, spl[1]);
        if (!f.exists()) return null;
        return f;
    }

    public Resource locateResource(String networkID, ResourceType type, Availability availability) {

    }


    /**
     * Network interfaces
     */

    public void transmitMessage(String message, Node node) {

    }

}
