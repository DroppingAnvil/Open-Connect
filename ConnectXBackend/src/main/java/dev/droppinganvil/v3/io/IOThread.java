package dev.droppinganvil.v3.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.droppinganvil.v3.ConnectX;
import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.network.UnauthorizedNetworkConnectivityException;
import dev.droppinganvil.v3.network.events.EventType;
import dev.droppinganvil.v3.network.events.NetworkContainer;
import dev.droppinganvil.v3.network.nodemesh.NodeConfig;
import dev.droppinganvil.v3.network.nodemesh.NodeMesh;
import dev.droppinganvil.v3.network.events.NetworkEvent;
import dev.droppinganvil.v3.utils.obj.BaseStatus;
import org.pgpainless.decryption_verification.OpenPgpMetadata;

import java.io.*;
import java.net.Socket;

public class IOThread implements Runnable {
    private static ObjectMapper mapper = new ObjectMapper();
    public boolean run = false;
    public Long sleep;
    public BaseStatus status;
    public ConnectX cx;

    public IOThread(Long sleep, ConnectX cx) {
        this.cx = cx;
        assert sleep != null;
        this.sleep = sleep;
    }

    public IOThread(Long sleep, BaseStatus bs, ConnectX cx) {
        this.cx = cx;
        assert sleep != null;
        this.sleep = sleep;
        status = bs;
    }
    @Override
    public void run() {
        while (run) {
            synchronized (cx.jobQueue) {
                IOJob ioJob = cx.jobQueue.poll();
                if (ioJob != null) {
                    try {
                        processJob(ioJob, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ioJob.success = false;
                        ioJob.doAfter(false);
                    }
                    ioJob.success = true;
                    ioJob.doAfter(true);
                } else {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static ByteArrayOutputStream reverse(InputStream is, Boolean closeAfter) throws IOException {
        //TODO
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[NodeConfig.ioReverseByteBuffer];
        while(is.read(buffer) > -1) {
            baos.write(buffer);
        }
        if (closeAfter) is.close();
        return baos;
    }
    public static void write(InputStream is, OutputStream os, Boolean closeAfter) throws IOException {
        //TODO
        byte[] buffer = new byte[NodeConfig.ioWriteByteBuffer];
        while(is.read(buffer) > -1) {
            os.write(buffer);
        }
        if (closeAfter) {
            is.close();
            os.close();
        }
    }
    public void processNetworkInput(InputStream is, Socket socket) throws IOException, DecryptionFailureException, ClassNotFoundException, UnauthorizedNetworkConnectivityException {
        //TODO optimize streams
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //TODO max size
        NetworkContainer nc;
        NetworkEvent ne;
        ByteArrayOutputStream baoss = new ByteArrayOutputStream();
        ByteArrayInputStream bais;
        String networkEvent = "";

        Object o = ConnectX.encryptionProvider.decrypt(is, baos);
        String networkContainer = baos.toString("UTF-8");
        try {
            nc = (NetworkContainer) ConnectX.deserialize("cxJSON1", networkContainer, NetworkContainer.class);
            if (nc.cxID != null) ConnectX.checkSafety(nc.cxID);
            if (!ConnectX.isProviderPresent(nc.serialization)) {
                socket.close();
                Analytics.addData(AnalyticData.Tear, "Unsupported serialization method "+nc.serialization);
                return;
            }
            Object o1 = ConnectX.encryptionProvider.decrypt()
            ne = ConnectX.deserialize(nc.serialization, )
        } catch (Exception e) {
            e.printStackTrace();
            if (NodeMesh.timeout.containsKey(socket.getInetAddress().getHostAddress())) {
                NodeMesh.blacklist.put(socket.getInetAddress().getHostAddress(), "Protocol not respected");
            } else {
                NodeMesh.timeout.put(socket.getInetAddress().getHostAddress(), 1000);
            }
            socket.close();
        }
        synchronized (NodeMesh.in.eventQueue) {
            NodeMesh.in.eventQueue.add(ne);
        }
    }
    public void signObject(IOJob ioJob) throws Exception {
        if (ioJob.is == null) {
            OutputStream oss = new ByteArrayOutputStream();
            cx.encryptionProvider.sign(null, oss);
            ConnectX.serialize(String.valueOf(ioJob.o1), ioJob.o, oss);
        } else {

        }
    }
    public boolean processJob(IOJob ioJob, boolean root) throws IOException {
        try {
            switch (ioJob.jt) {
                case WRITE:
                    write(ioJob.is, ioJob.os, ioJob.closeAfter);
                    break;
                case REVERSE:
                    ioJob.os = reverse(ioJob.is, ioJob.closeAfter);
                    break;
                case NETWORK_READ:
                    NetworkInputIOJob ioj = (NetworkInputIOJob) ioJob;
                    processNetworkInput(ioJob.is, ioj.ina);
                    break;
                case SIGN_OBJECT:
            }
            if (ioJob.next != null) {
                processJob(ioJob.next, false);
            }
            if (!root) {
                ioJob.success = true;
                ioJob.doAfter(true);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ioJob.success = false;
            ioJob.doAfter(false);
            return false;
        }
    }
}
