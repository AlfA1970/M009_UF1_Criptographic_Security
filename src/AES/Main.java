package AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    String KEY = "criptographicSecurity";

    public static void main(String[] args) {
        Main main = new Main();
        JOptionPane.showMessageDialog(null,main.Encrypt("hello"));


    }

    // Encrypt / Decrypt Class

    public SecretKeySpec CreateKey(String key){
        try{
            byte[]string = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            string = md.digest(string);
            string = Arrays.copyOf(string,10);
            SecretKeySpec sk = new SecretKeySpec(string,"AES");
            return  sk;

        }catch (Exception e){
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
            String encruptedString = Base64.getEncoder().encodeToString(encrypted);
            return encruptedString;

        }catch (Exception e){
            return null;
        }

    }

    // Decryptation method
}
