package dev.droppinganvil.v2.crypt.pgpainless;

import dev.droppinganvil.v2.crypt.core.CryptProvider;
import dev.droppinganvil.v2.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v2.crypt.core.exceptions.EncryptionFailureException;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.pgpainless.PGPainless;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;

import java.io.*;

public class PainlessCryptProvider extends CryptProvider {

    public PainlessCryptProvider() {
        super("PGP E2E Encryption", "Core");
    }
    private static PGPSecretKeyRing secretKeys;
    private static PGPPublicKeyRing publicKeys;

    @Override
    public void encrypt(InputStream is, OutputStream os, String publicKey) throws EncryptionFailureException {
        EncryptionStream encryptionStream = PGPainless.encryptAndOrSign()
                .onOutputStream(os)
                .withOptions(
                        ProducerOptions.signAndEncrypt(
                                new EncryptionOptions()
                                        .addRecipient(),
                                new SigningOptions()
                                        // Sign in-line (using one-pass-signature packet)
                                        .addInlineSignature(secretKeyDecryptor, aliceSecKey, signatureType)
                                        // Sign using a detached signature
                                        .addDetachedSignature(secretKeyDecryptor, aliceSecKey, signatureType)
                                        // optionally override hash algorithm
                                        .overrideHashAlgorithm(HashAlgorithm.SHA256)
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
            secretKeys = PGPainless.readKeyRing().secretKeyRing(privateKeyFile.toURL().openStream());
        } else {
            secretKeys = PGPainless.generateKeyRing()
                    .modernKeyRing("IPX <ipx@droppinganvil.dev>", s);
            FileOutputStream fos = new FileOutputStream(privateKeyFile);
            secretKeys.encode(fos);
        }
        File publicKeyFile = new File(dir, "publickey.txt");
        if (publicKeyFile.exists()) {
            publicKeys = PGPainless.readKeyRing().publicKeyRing(publicKeyFile.toURL().openStream());
        } else {
//
        }

        //load keys
        ready = true;
    }
}
