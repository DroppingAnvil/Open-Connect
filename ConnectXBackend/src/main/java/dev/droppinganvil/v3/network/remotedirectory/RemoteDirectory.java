package dev.droppinganvil.v3.network.remotedirectory;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.io.IOJob;
import dev.droppinganvil.v3.io.IOThread;
import dev.droppinganvil.v3.utils.permissions.BasicPermissionContainer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;

/**
 * Object for accessing files stored remotely or locally
 */
public class RemoteDirectory {
    public static OkHttpClient httpClient = new OkHttpClient();
    /**
     * RemoteDirectory URLs should be formatted ip/rdir?id=containerID?file=
     * RemoteDirectory URLs, when using depth, should look similar to ip/rdir?id=containerID?file=dir1/dir2/
     * If used with the ConnectX system user base containers are the user's id
     */
    public String url;
    public boolean readOnly;
    /**
     * Location of file in server scope
     */
    public String serverID;
    /**
     * Location of directory in machine scope
     */
    public String machinePath;
    /**
     * Permission container for directory
     */
    public BasicPermissionContainer directoryPermissions;
    public Boolean useDepthIfAvailable = false;
    private String auth;

    public RemoteDirectory(String address, String directoryPath, String auth) {
        this.auth = auth;
        machinePath = directoryPath;
        this.url = address;
        if (isLocal()) {

        }
    }

    public void copyFile(String path, OutputStream os, Boolean sync) throws IOException {
        InputStream is;
        //TODO Validity checks
        if (isLocal()) {
            is = new FileInputStream(path);
        } else {
            Response r = httpClient.newCall(new Request.Builder().url( + path).build()).execute();
            ResponseBody rb = r.body();
            if (rb == null) throw new FileNotFoundException();
            is = rb.byteStream();
        }
        IOJob ioJob = new IOJob(is, os, true);
        if (sync) {
            IOThread.processJob(ioJob, true);
        } else {
            synchronized (IOThread.jobQueue) {
                IOThread.jobQueue.add(ioJob);
            }
        }
    }

    public void loadPermissions() {
        if (!isLocal()) return;
        File permissionFile = new File()
    }

    public boolean uploadFile(File f) {

    }

    public boolean uploadFile(FileOutputStream fos) {

    }

    public boolean isLocal() {
        return serverID.equals(Configuration.serverID);
    }


}
