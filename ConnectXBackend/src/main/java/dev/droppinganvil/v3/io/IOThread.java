package dev.droppinganvil.v3.io;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.crypt.core.CryptServiceProvider;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.network.nodemesh.NetworkContainer;
import dev.droppinganvil.v3.utils.obj.BaseStatus;
import org.pgpainless.decryption_verification.OpenPgpMetadata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IOThread implements Runnable {
    public static boolean run = false;
    public static final Queue<IOJob> jobQueue = new ConcurrentLinkedQueue<>();
    public Long sleep;
    public BaseStatus status;

    public IOThread(Long sleep) {
        assert sleep != null;
        this.sleep = sleep;
    }

    public IOThread(Long sleep, BaseStatus bs) {
        assert sleep != null;
        this.sleep = sleep;
        status = bs;
    }
    @Override
    public void run() {
        while (run) {
            IOJob ioJob = jobQueue.poll();
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
    public static ByteArrayOutputStream reverse(InputStream is, Boolean closeAfter) throws IOException {
        //TODO
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[Configuration.IO_REVERSE_BYTE_BUFFER];
        while(is.read(buffer) > -1) {
            baos.write(buffer);
        }
        if (closeAfter) is.close();
        return baos;
    }
    public static void write(InputStream is, OutputStream os, Boolean closeAfter) throws IOException {
        //TODO
        byte[] buffer = new byte[Configuration.IO_WRITE_BYTE_BUFFER];
        while(is.read(buffer) > -1) {
            os.write(buffer);
        }
        if (closeAfter) {
            is.close();
            os.close();
        }
    }
    public static void processNetworkContainer(InputStream is) throws IOException, DecryptionFailureException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Object o = CryptServiceProvider.encryptionProvider.decrypt(is, baos);
        if (o instanceof OpenPgpMetadata) {
            OpenPgpMetadata opm = (OpenPgpMetadata) o;
            //TODO
        }

        NetworkContainer nc = ()

    }
    public static boolean processJob(IOJob ioJob, boolean root) throws IOException {
        try {
            switch (ioJob.jt) {
                case WRITE:
                    write(ioJob.is, ioJob.os, ioJob.closeAfter);
                    break;
                case REVERSE:
                    ioJob.os = reverse(ioJob.is, ioJob.closeAfter);
                    break;
                case DECRYPT:

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
