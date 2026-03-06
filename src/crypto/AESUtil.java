package crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import crypto.HashUtil;


public class AESUtil {

	public static SecretKey getKeyFromPassword(String password) throws Exception {
	    String hashedPassword = HashUtil.sha256(password); // SHA-256 hash
	    byte[] keyBytes = hashedPassword.substring(0, 32).getBytes(); // first 32 chars for AES-256
	    return new SecretKeySpec(keyBytes, "AES");
	}


    public static void encryptFile(File inputFile, File outputFile, SecretKey key) throws Exception {
        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(outputBytes);
        }
    }

    public static void decryptFile(File inputFile, File outputFile, SecretKey key) throws Exception {
        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(outputBytes);
        }
    }
}
