package dev.droppinganvil.v2.crypt.core.embedded;

import dev.droppinganvil.v2.Configuration;
import dev.droppinganvil.v2.crypt.core.CryptProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CryptClient implements Runnable {
    public File rootDIR;
    public File orgDIR;
    private CryptProvider provider;
    public static OkHttpClient httpClient = new OkHttpClient();
    private String s;

    public CryptClient(File root, CryptProvider provider, String s) {
        this.s = s;
        this.rootDIR = root;
        this.provider = provider;
        generateFileStructure();
    }

    public boolean downloadPublicKey() throws IOException {
        Response r = httpClient.newCall(new Request.Builder().url(Configuration.CRYPT_CONNECT_URL).build()).execute();
        ResponseBody rb = r.body();
        if (rb == null) return false;
        FileOutputStream fos = new FileOutputStream("publickey.txt");
        fos.write(rb.bytes());
        fos.close();
        if (!provider.ready) {
            try {
                provider.setup(s, orgDIR);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void generateFileStructure() {
        File crypt = new File(rootDIR, "Crypt");
        if (!crypt.exists()) crypt.mkdir();
        orgDIR = new File(crypt, Configuration.CRYPT_CONNECT_ORG);
        if (!orgDIR.exists()) orgDIR.mkdir();
    }

    @Override
    public void run() {

    }
}
