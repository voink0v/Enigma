import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class Encryptor extends BaseEncryptorDecryptor {


    public Encryptor(Key key, IvParameterSpec iv) {
        this.key = key;
        this.iv = iv;
    }

    public String encrypt(String input)
            throws Exception {

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
    }

}
