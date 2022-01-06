/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.pgpainless.PainlessCryptProvider;
import dev.droppinganvil.v3.edge.ConnectXContainer;
import dev.droppinganvil.v3.io.IOJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectX {
    public static Platform platform;
    final static Logger logger = LoggerFactory.getLogger(ConnectX.class);
    public CryptProvider encryptionProvider = new PainlessCryptProvider();
    public final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public File cxRoot = new File("ConnectX");
    public ConnectX() throws IOException {
        String osS = System.getProperty("os.name");
        osS = osS.toLowerCase(Locale.ROOT);
        if (osS.contains("linux")) {
            platform = Platform.LINUX_GENERIC;
        } else if (osS.contains("windows")) {
            platform = Platform.WINDOWS;
        } else if (osS.contains("osx")) {
            platform = Platform.OSX;
        }
        if (!cxRoot.exists()) {
            if (!cxRoot.mkdir()) throw new IOException();
        }
    }
}
