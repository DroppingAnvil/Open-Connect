package dev.droppinganvil.v3.crypt.pgpainless;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.crypt.core.exceptions.EncryptionFailureException;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.DocumentSignatureType;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.decryption_verification.OpenPgpMetadata;
import org.pgpainless.encryption_signing.EncryptionOptions;
import org.pgpainless.encryption_signing.EncryptionStream;
import org.pgpainless.encryption_signing.ProducerOptions;
import org.pgpainless.encryption_signing.SigningOptions;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.pgpainless.key.util.KeyRingUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PainlessCryptProvider extends CryptProvider {

    public PainlessCryptProvider() {
        super("Encryption Layer", "Core");
    }

    /**
     * On board secret key
     */
    private static PGPSecretKeyRing secretKey;
    /**
     * All network public keys
     */
    public static PGPPublicKeyRingCollection publicKeys;
    /**
     * On board public key
     */
    private PGPPublicKeyRing publicKey;
    /**
     * NMI Public key
     */
    public PGPPublicKeyRing nmipubkey;
    private SecretKeyRingProtector protector = SecretKeyRingProtector.unprotectedKeys();

    @Override
    public void encrypt(InputStream is, OutputStream os, Long publicKeyL) throws EncryptionFailureException {
        try {
            EncryptionStream encryptor = PGPainless.encryptAndOrSign()
                    .onOutputStream(os)
                    .withOptions(ProducerOptions.signAndEncrypt(
                            EncryptionOptions.encryptCommunications()
                                    .addRecipient(publicKeys.getPublicKeyRing(publicKeyL))
                                    .addRecipient(publicKey),
                            new SigningOptions()
                                    .addInlineSignature(protector, secretKey, DocumentSignatureType.CANONICAL_TEXT_DOCUMENT)
                            ).setAsciiArmor(false)
                    );
        } catch (Exception e) {
            EncryptionFailureException efe = new EncryptionFailureException();
            efe.initCause(e);
            throw efe;
        }
    }
    @Override
    public Object decrypt(InputStream is, OutputStream os, String deviceID) throws DecryptionFailureException {
        try {
            DecryptionStream decryptionStream = PGPainless.decryptAndOrVerify()
                    .onInputStream(is)
                    .withOptions(new ConsumerOptions()
                            .addDecryptionKey(secretKey, protector)
                            .addVerificationCerts(publicKeys)
                    );
            Streams.pipeAll(decryptionStream, os);
            decryptionStream.close();
            return decryptionStream.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            DecryptionFailureException dfe = new DecryptionFailureException();
            dfe.initCause(e);
            throw dfe;
        }
    }
    @Override
    public void setup(String s, File dir) throws Exception {
        File privateKeyFile = new File(dir, "privatekey.txt");
        if (privateKeyFile.exists()) {
            secretKey = PGPainless.readKeyRing().secretKeyRing(privateKeyFile.toURL().openStream());
        } else {
            secretKey = PGPainless.generateKeyRing()
                    .modernKeyRing("ConnectX <contact@anvildevelopment.us>", s);
            FileOutputStream fos = new FileOutputStream(privateKeyFile);
            secretKey.encode(fos);
        }
        publicKey = KeyRingUtils.publicKeyRingFrom(secretKey);
        File publicKeyFile = new File(dir, "publickeys.txt");
        if (publicKeyFile.exists()) {
            publicKeys = PGPainless.readKeyRing().publicKeyRingCollection(publicKeyFile.toURL().openStream());
        } else {
            List<PGPPublicKeyRing> empty = new ArrayList<>();
            publicKeys = new PGPPublicKeyRingCollection(empty);
        }
        File nmipublicKeyFile = new File(dir, "nmi.asc");
        if (nmipublicKeyFile.exists()) {
            nmipubkey = PGPainless.readKeyRing().publicKeyRing(nmipublicKeyFile.toURL().openStream());
        } else {
            throw new IOException();
        }


        //load keys
        ready = true;
    }
}
