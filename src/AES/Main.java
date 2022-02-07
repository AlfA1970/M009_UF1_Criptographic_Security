package AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    String KEY = "";

    public static void main(String[] args) {
        Main main = new Main();
        JOptionPane.showMessageDialog(null,main.Encrypt("Hello"));

    }

    // Encrypt / Decrypt Class

    public SecretKeySpec CreateKey(String key){
        try{
            byte[]string = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            string = md.digest(string);
            string = Arrays.copyOf(string,16);
            return new SecretKeySpec(string,"AES");

        }catch (Exception e){
            System.out.println("Error createKey");
            return null;
        }
    }

    // Encryptation method

    public String Encrypt(String encrypt){
        try{
            SecretKeySpec ske = CreateKey(KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,ske);
            byte[]string = encrypt.getBytes(StandardCharsets.UTF_8);
            byte[]encrypted = cipher.doFinal(string);
            return Base64.getEncoder().encodeToString(encrypted);

        }catch (Exception e){
            System.out.println("Error Encrypt");
            return null;
        }
    }

    // Decryptation method

    public String Decrypt(String encrypt){
        
    }
}
