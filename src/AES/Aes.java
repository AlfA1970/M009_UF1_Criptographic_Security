package AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Aes {

    String KEY = "ENCRYPT&DECRYPT: METHODS";

    public static void main(String[] args) throws InterruptedException {
        String toEncrypt = "";
        String encrypted_string = "";
        Aes main = new Aes();
        Scanner textToEncrypt = new Scanner(System.in);
        textToEncrypt.useDelimiter("\n");
        System.out.print("Enter string to encrypt: ");
        toEncrypt =  textToEncrypt.next();//JOptionPane.showInputDialog("Enter string to encrypt: ");
        encrypted_string = main.Encrypt(toEncrypt);
        Thread.sleep(1300);
        System.out.println("Encrypted string: " + encrypted_string);
        Thread.sleep(1300);
        System.out.println("Decrypted string: " + main.Decrypt(encrypted_string));

    }

    // Encrypt / Decrypt Class

    public SecretKeySpec CreateKey(String key){
        try{
            byte[]string = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            string = md.digest(string);
            // For diferents kind of ciphers, change  newlength parameter to 16,24 o 32
            // for encryptations of 128,192 and 256 bits. ONLY FOR AES algorithm
            string = Arrays.copyOf(string,32);
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

    public String Decrypt(String decrypt){
        try{
            SecretKeySpec ske = CreateKey(KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,ske);

            byte[]string = Base64.getDecoder().decode(decrypt);
            byte[]decrypted = cipher.doFinal(string);
            String decryptedString = new String(decrypted);
            return decryptedString;

        }catch (Exception e){
            System.out.println("Error Decrypt");
            e.printStackTrace();
            return null;
        }
    }
}
