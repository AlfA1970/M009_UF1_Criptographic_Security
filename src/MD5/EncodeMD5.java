package MD5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class EncodeMD5 {

    public String secretKey = "WeAreProgrammers!";

    public static void main(String[] args) throws InterruptedException {
        EncodeMD5 md5 = new EncodeMD5();
        Scanner textToEncryp = new Scanner(System.in);
        textToEncryp.useDelimiter("\n");
        System.out.print("Enter message to encrypt: ");
        String messageToEncrypt = textToEncryp.next();
        String stringEncrypted = md5.encode(md5.secretKey, messageToEncrypt);
        Thread.sleep(1300);
        System.out.println("String Encrypted: " + stringEncrypted);
        Thread.sleep(1300);
        String stringDecrypted = md5.decode(md5.secretKey, stringEncrypted);
        System.out.println("String Decrypted: " + stringDecrypted);

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
