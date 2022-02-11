package MD5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class EncodeMD5 {

    public String secretKey = "WeAreProgrammers!";

    public static void main(String[] args) {
        EncodeMD5 md5 = new EncodeMD5();
        String messageToEncrypt = JOptionPane.showInputDialog("Enter message to encrypt: ");
        String stringEncrypted = md5.encode(md5.secretKey, messageToEncrypt);
        JOptionPane.showMessageDialog(null, "String Encrypted: " + stringEncrypted);
        String stringDecrypted = md5.decode(md5.secretKey, stringEncrypted);
        JOptionPane.showMessageDialog(null, "String Decrypted: " + stringDecrypted);

    }

    public String encode(String key, String stringToEncrypt) {
        String encryptation = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passkey = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[] byteKey = Arrays.copyOf(passkey, 24);
            SecretKey skey = new SecretKeySpec(byteKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            byte[] plainTextBytes = stringToEncrypt.getBytes(StandardCharsets.UTF_8);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] stringB64 = Base64.getEncoder().encode(buf);
            encryptation = new String(stringB64);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }

        return encryptation;
    }

    public String decode(String key, String stringToEncrypt) {
        String decryptation = "";

        try{
            byte[]stringEncrypt = Base64.getDecoder().decode(stringToEncrypt.getBytes(StandardCharsets.UTF_8));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passkeyEncoded = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[] byteKey = Arrays.copyOf(passkeyEncoded, 24);
            SecretKey skey = new SecretKeySpec(byteKey, "AES");
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE, skey);
            byte[] plainTextBytes = decipher.doFinal(stringEncrypt);
            decryptation = new String(plainTextBytes, StandardCharsets.UTF_8);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
        return decryptation;
    }
}
