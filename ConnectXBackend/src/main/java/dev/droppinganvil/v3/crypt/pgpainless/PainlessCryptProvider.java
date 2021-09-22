package dev.droppinganvil.v3.crypt.pgpainless;

import dev.droppinganvil.v3.crypt.core.CryptProvider;
import dev.droppinganvil.v3.crypt.core.exceptions.DecryptionFailureException;
import dev.droppinganvil.v3.crypt.core.exceptions.EncryptionFailureException;
import org.bouncycastle.openpgp.*;
import org.pgpainless.PGPainless;
import org.pgpainless.algorithm.DocumentSignatureType;
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
    private static PGPSecretKeyRing secretKey;
    private static PGPPublicKeyRingCollection publicKeys;
    private PGPPublicKeyRing publicKey;
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
                            ).setAsciiArmor(true)
                    );
        } catch (Exception e) {
            EncryptionFailureException efe = new EncryptionFailureException();
            efe.initCause(e);
            throw efe;
        }
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
        publicKey = KeyRingUtils.publicKeyRingFrom(secretKey);
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
