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
import dev.droppinganvil.v3.network.nodemesh.NodeMesh;
import dev.droppinganvil.v3.resourcecore.Availability;
import dev.droppinganvil.v3.resourcecore.Resource;
import dev.droppinganvil.v3.resourcecore.ResourceType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public static final Queue<InputBundle> eventQueue = new ConcurrentLinkedQueue<>();
    public static File cxRoot = new File("ConnectX");
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
            File nodemesh = new File(cxRoot, "nodemesh");
            if (!nodemesh.exists()) if (!nodemesh.mkdir()) throw new IOException();
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
    public static Object getSignedObject(String cxID, InputStream is, Class<?> clazz) {

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


}
