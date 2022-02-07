package AES;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Main {

    String KEY = "criptographicSecurity";

    public static void main(String[] args) {


    }

    // Encrypt / Decrypt Class

    public SecretKeySpec createKey(String key){
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

    }

    // Decryptation method
}
