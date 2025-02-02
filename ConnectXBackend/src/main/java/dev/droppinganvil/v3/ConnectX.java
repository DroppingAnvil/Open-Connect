/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import dev.droppinganvil.v3.api.CXPlugin;
import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.pgpainless.PainlessCryptProvider;
import dev.droppinganvil.v3.exceptions.UnsafeKeywordException;
import dev.droppinganvil.v3.io.IOJob;
import dev.droppinganvil.v3.io.strings.JacksonProvider;
import dev.droppinganvil.v3.io.strings.SerializationProvider;
import dev.droppinganvil.v3.network.CXNetwork;
import dev.droppinganvil.v3.network.InputBundle;
import dev.droppinganvil.v3.network.events.NetworkEvent;
import dev.droppinganvil.v3.network.nodemesh.Node;
import dev.droppinganvil.v3.network.nodemesh.OutputBundle;
import dev.droppinganvil.v3.network.nodemesh.PeerDirectory;
import dev.droppinganvil.v3.network.nodemesh.bridge.CXBridge;
import dev.droppinganvil.v3.resourcecore.Availability;
import dev.droppinganvil.v3.resourcecore.Resource;
import dev.droppinganvil.v3.resourcecore.ResourceType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectX {
    public static Platform platform;
    public State state = State.CXConnecting;
    private static ConcurrentHashMap<String, CXNetwork> networkMap = new ConcurrentHashMap<>();
    public static final CryptProvider encryptionProvider = new PainlessCryptProvider();
    private static final transient ConcurrentHashMap<String, SerializationProvider> serializationProviders = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, CXBridge> bridgeMap = new ConcurrentHashMap<>();
    public final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public static final Queue<InputBundle> eventQueue = new ConcurrentLinkedQueue<>();
    public static final Queue<OutputBundle> outputQueue = new ConcurrentLinkedQueue<>();
    public static File cxRoot = new File("ConnectX");
    public static File nodemesh;
    public static File resources;
    private transient static CXNetwork cx;
    private static transient Node self;
    private static ConcurrentHashMap<String, CXPlugin> plugins = new ConcurrentHashMap<>();
    private static transient List<String> reserved = Arrays.asList("SYSTEM", "CX", "cxJSON1");


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

        serializationProviders.put("cxJSON1", new JacksonProvider());

        //Setup filesystem
        if (!cxRoot.exists()) {
            if (!cxRoot.mkdir()) throw new IOException();
            nodemesh = new File(cxRoot, "nodemesh");
            if (!nodemesh.exists()) if (!nodemesh.mkdir()) throw new IOException();
            resources = new File(nodemesh, "nodemesh-resources");
            if (!resources.exists()) if (!resources.mkdir()) throw new IOException();
        }
        //TODO network join

    }
    public static void checkSafety(String s) throws UnsafeKeywordException {
        //TODO filesystem safety
        if (!reserved.contains(s)) throw new UnsafeKeywordException();
    }
    public static void checkProvider(String method) {
        if (!serializationProviders.containsKey(method)) throw new NullPointerException();
    }
    public static boolean isProviderPresent(String method) {
        return serializationProviders.containsKey(method);
    }
    public static String serialize(String method, Object o) throws Exception {
        checkProvider(method);
        return serializationProviders.get(method).getString(o);
    }
    public static void serialize(String method, Object o, OutputStream os) throws Exception {
        checkProvider(method);
        serializationProviders.get(method).writeToStream(os, o);
    }
    public static Object deserialize(String method, String s, Class<?> clazz) throws Exception {
        checkProvider(method);
        return serializationProviders.get(method).getObject(s, clazz);
    }
    public static Object deserialize(String method, InputStream is, Class<?> clazz) throws Exception {
        checkProvider(method);
        return serializationProviders.get(method).getObject(is, clazz);
    }
    public static void addSerializationProvider(String name, SerializationProvider provider) throws UnsafeKeywordException, IllegalAccessException {
        checkSafety(name);
        if (serializationProviders.containsKey(name)) throw new IllegalAccessException();
        serializationProviders.put(name, provider);
    }
    public static Object getSignedObject(String cxID, InputStream is, Class<?> clazz, String method) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (ConnectX.encryptionProvider.verifyAndStrip(is, baos, cxID)) {
            return deserialize(method, baos.toString("UTF-8"), clazz);
        }
        return null;
    }
    public static ByteArrayOutputStream signObject(Object o, Class<?> clazz, String method) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String s = serialize(method, o);
        ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        ConnectX.encryptionProvider.sign(bais, baos);
        bais.close();
        return baos;
    }
    public static String getOwnID() {
        return self.cxID;
    }
    public void connect() {}
    public static boolean checkGlobalPermission(String cxID, String permission) {
        assert cx != null;
        assert !cxID.contains("SYSTEM");
        return cx.checkNetworkPermission(cxID, permission);
    }
    public Resource locateResource(String networkID, ResourceType type, Availability availability) {

    }
    public static boolean addPlugin(CXPlugin cxp) {
        try {
            checkSafety(cxp.serviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (plugins.containsKey(cxp.serviceName)) return false;
        plugins.put(cxp.serviceName, cxp);
        return true;
    }
    public static boolean sendPluginEvent(NetworkEvent ne, String eventType) {
        if (!plugins.containsKey(eventType)) return false;
        return plugins.get(eventType).handleEvent(ne);
    }
    public static boolean recordEvent(NetworkEvent ne) {
        if (ne.target.equalsIgnoreCase("cx")) {

        }
    }

    public static CXNetwork getNetwork(String networkID) {
        if (!networkMap.containsKey(networkID)) return null;
        return networkMap.get(networkID);
    }

    public static void loadLan() {
        File lan = new File(cxRoot, "lan");
        if (!lan.exists()) lan.mkdir();
        for (File f : lan.listFiles()) {
            if (f.getName().contains(".cxi")) {
                Node n = getSignedObject(getOwnID(), f.toURL().openStream(), Node.class, "cxJSON1");
                PeerDirectory.lan.put(n.cxID, n);
            }
        }
    }

    public static boolean isPeerLoaded(String cxID) {
        return PeerDirectory.peerCache.containsKey(cxID);
    }

    public static void loadPeer(String cxID, boolean sync, boolean lookups) {
        PeerDirectory.
    }

    public static boolean connectBridge(CXBridge cxBridge) {
        if (bridgeMap.containsKey(cxBridge.protocol)) return false;
        bridgeMap.put(cxBridge.)
    }


}
