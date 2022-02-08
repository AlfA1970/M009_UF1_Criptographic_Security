package MD5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class EncodeMD5 {

    String secretKey="WeAreProgrammers!";

    public static void main(String[] args) {

    }

    public String encode(String key, String stringToEncrypt){
        String encryptation;

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[]passkey = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[]byteKey = Arrays.copyOf(passkey, 24);
            SecretKey skey = new SecretKeySpec(byteKey,"DESed");
            Cipher cipher = Cipher.getInstance("DESed");
            cipher.init(Cipher.ENCRYPT_MODE,skey);
            byte[]plainTextBytes =  stringToEncrypt.getBytes(StandardCharsets.UTF_8);
            byte[]buf = cipher.doFinal(plainTextBytes);
            byte[]stringB64 = Base64.getEncoder().encode(buf);
            encryptation = new String(stringB64);

            return encryptation;

        }catch (Exception e){
            System.out.println("Something went wrong!");
        }

        return null;

    }

    public String decode(String key, String stringToEncrypt){

        return null;

    }
}
