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
    public void encrypt(InputStream is, OutputStream os, String publicKey) throws EncryptionFailureException {

    }
    public void encryptFile(File in, File outDir, String publicKey) throws IOException, EncryptionFailureException {
        if (!in.exists() || IPXFileUtils.checkBasicIORights(in)) throw new IOException();
            String name = in.getName();
            File destination = new File(outDir, name);
            if (destination.exists() && IPXFileUtils.checkBasicIORights(destination)) {
                if (!destination.delete()) throw new IOException();
            }
            FileInputStream input = new FileInputStream(in);
            FileOutputStream encryptedOutput = new FileOutputStream(destination);
            encrypt(input, encryptedOutput, publicKey);
            input.close();
            encryptedOutput.flush();
            encryptedOutput.close();
            if (!in.delete()) throw new IOException();
    }
    public void decryptFile(File in, File outDir) throws IOException, DecryptionFailureException {
        if (!in.exists() || IPXFileUtils.checkBasicIORights(in)) throw new IOException();
        String name = in.getName();
        File destination = new File(outDir, name);
        if (destination.exists() && IPXFileUtils.checkBasicIORights(destination)) {
            if (!destination.delete()) throw new IOException();
        }
        FileInputStream input = new FileInputStream(in);
        FileOutputStream decryptedOutput = new FileOutputStream(destination);
        decrypt(input, decryptedOutput);
        input.close();
        decryptedOutput.flush();
        decryptedOutput.close();
    }
    public void decrypt(InputStream is, OutputStream os) throws DecryptionFailureException {

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
    public void setup(String s, File dir) throws Exception {

    }


    public void doSelfTest() {
        try {
            //TODO better testing
            doFileStructureCheck();
        } catch (IOException e) {
            e.printStackTrace();
            //Ignore for now
        } catch (CryptInternalVerificationException e) {
            e.printStackTrace();
            initiateShutdown(e.getMessage(), false);
        }
    }
    public void initiateShutdown(String s, Boolean normal) {
            System.out.println("---- Crypt System Shutdown ----");
            System.out.println("The current CryptProvider will be flushed and disabled");
            if (!normal) {
                System.out.println("Please contact your system administrator before continuing");
                System.out.print("This is a serious security concern and could lead to more system failures depending on implementation");
                System.out.println("Extra Data");
                System.out.println(s);
            } else {
                System.out.println("Received normal shutdown instruction, if this was not intentional contact your system administrator immediately");
            }
            System.out.println("---- Crypt System Shutdown ----");
    }

}
