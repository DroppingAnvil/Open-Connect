package dev.droppinganvil.v3.crypt.core;

import dev.droppinganvil.v3.crypt.core.exceptions.CryptInternalVerificationException;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.crypt.core.exceptions.EncryptionFailureException;
import dev.droppinganvil.v3.crypt.core.exceptions.TempDirectorySolidified;
import dev.droppinganvil.v3.utils.IPXFileUtils;
import dev.droppinganvil.v3.utils.obj.BaseStatus;

import java.io.*;
import java.util.HashSet;

public abstract class CryptProvider {
    private HashSet<String> eraseQueue = new HashSet<>();
    private File tempdir;
    public BaseStatus status;
    public boolean ready = false;

    public CryptProvider(String serviceName, String serviceCategory) {
        status = new BaseStatus(serviceName, serviceCategory);
        status.setVMPair();
    }
    public String getPublicKey() {
        return null;
    }

    /**
     * @param is Signed input
     * @param os Output stripped of all encryption meta
     * @return Verification status (True being successful)
     */
    public boolean verifyAndStrip(InputStream is, OutputStream os, String cxID) throws DecryptionFailureException {
        return false;
    }
    public void encrypt(InputStream is, OutputStream os, String cxID) throws EncryptionFailureException {

    }
    public void sign(InputStream is, OutputStream os) throws EncryptionFailureException {

    }
    public void encryptCopyAndDeleteFile(File in, File outDir, String cxID) throws IOException, EncryptionFailureException {
        if (!in.exists() || IPXFileUtils.checkBasicIORights(in)) throw new IOException();
            String name = in.getName();
            File destination = new File(outDir, name);
            if (destination.exists() && IPXFileUtils.checkBasicIORights(destination)) {
                if (!destination.delete()) throw new IOException();
            }
            FileInputStream input = new FileInputStream(in);
            FileOutputStream encryptedOutput = new FileOutputStream(destination);
            encrypt(input, encryptedOutput, cxID);
            if (!in.delete()) throw new IOException();
    }
    public Object decrypt(InputStream is, OutputStream os, String cxID, boolean tryImport) throws DecryptionFailureException {
        return null;
    }
    public Boolean verify(String data, String cxID) throws DecryptionFailureException {
        return false;
    }
    private File createTemp(String s) {
        return null;
    }
    private void doFileStructureCheck() throws CryptInternalVerificationException, IOException {
        try {
            if (tempdir == null) throw new NullPointerException();
            if (!IPXFileUtils.checkIORights(tempdir)) {
                throw new CryptInternalVerificationException();
            }
        } catch (Exception e) {
            if (e instanceof CryptInternalVerificationException) throw e;
            CryptInternalVerificationException cive = new CryptInternalVerificationException();
            cive.initCause(e);
            throw cive;
        }
    }
    public void setTempDir(File dir) throws TempDirectorySolidified {
        if (this.tempdir != null) throw new TempDirectorySolidified();
        tempdir = dir;
    }
    public void setup(String cxID, String s, File dir) throws Exception {

    }
    public boolean cacheCert(String cxID, boolean tryImport, boolean sync) {return false;}
    public void shutdown() {

    }


    public void doSelfTest() {

    }

}
