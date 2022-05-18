/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.threads;

import dev.droppinganvil.v3.io.IOThread;
import dev.droppinganvil.v3.io.NetworkInputIOJob;
import dev.droppinganvil.v3.network.nodemesh.InConnectionManager;

import java.io.IOException;
import java.net.Socket;

public class SocketWatcher implements Runnable {
    public static boolean active = true;
    @Override
    public void run() {
        while (active) {
            try {
                Socket s = InConnectionManager.serverSocket.accept();
                if (s != null) {
                    NetworkInputIOJob ni = new NetworkInputIOJob(s.getInputStream(), s.getInetAddress().getHostAddress());
                    synchronized (IOThread.jobQueue) {
                        IOThread.jobQueue.add(ni);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
