package dev.droppinganvil.v3.crypt.pgpainless;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.crypt.core.exceptions.EncryptionFailureException;
import org.bouncycastle.openpgp.*;
import org.pgpainless.PGPainless;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PainlessCryptProvider extends CryptProvider {

    public PainlessCryptProvider() {
        super("PGP E2E Encryption", "Core");
    }
    private static PGPSecretKeyRing secretKey;
    private static PGPPublicKeyRingCollection publicKeys;

    @Override
    public void encrypt(InputStream is, OutputStream os, String publicKey) throws EncryptionFailureException {
        PGPPublicKeyRing publicKeys1 = new PGPPublicKeyRing()
        EncryptionStream encryptionStream = PGPainless.encryptAndOrSign()
                .onOutputStream(os)
                .withOptions(
                        ProducerOptions.signAndEncrypt(
                                new EncryptionOptions()
                                        .addRecipient(publicKey)
                        ).setAsciiArmor(true) // Ascii armor or not
                );
    }
    @Override
    public void decrypt(InputStream is, OutputStream os) throws DecryptionFailureException {

    }
    @Override
    public void setup(String s, File dir) throws Exception {
        File privateKeyFile = new File(dir, "privatekey.txt");
        if (privateKeyFile.exists()) {
            secretKey = PGPainless.readKeyRing().secretKeyRing(privateKeyFile.toURL().openStream());
        } else {
            secretKey = PGPainless.generateKeyRing()
                    .modernKeyRing("ControlX <contact@anvildevelopment.us>", s);
            FileOutputStream fos = new FileOutputStream(privateKeyFile);
            secretKey.encode(fos);
        }
        File publicKeyFile = new File(dir, "publickeys.txt");
        if (publicKeyFile.exists()) {
            publicKeys = PGPainless.readKeyRing().publicKeyRingCollection(publicKeyFile.toURL().openStream());
        } else {
            List<PGPPublicKeyRing> empty = new ArrayList<>();
            publicKeys = new PGPPublicKeyRingCollection(empty);
        }


        //load keys
        ready = true;
    }
}
