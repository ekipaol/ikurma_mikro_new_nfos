package com.application.bris.ikurma.util.service_encrypt;

import com.application.bris.ikurma.BuildConfig;
import java.security.MessageDigest;
import java.util.Arrays;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESHelper {
    private String _secretKey;

    public DESHelper() {
        _secretKey = BuildConfig.SECRET_KEY;
    }

    public String encrypt(String message) {

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(_secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = message.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            String base64EncryptedString = Base64.encodeToString(buf,Base64.NO_WRAP);
            return base64EncryptedString;
        }
        catch(Exception e){
            return null;
        }
    }

    public String decrypt(String encryptedText) {
        try {
            byte[] message = Base64.decode(encryptedText,Base64.NO_WRAP);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(_secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            return new String(plainText, "UTF-8");
        }
        catch (Exception ex) {
            return null;
        }
    }
}