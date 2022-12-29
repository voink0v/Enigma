import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class Decryptor extends BaseEncryptorDecryptor {


    public Decryptor(Key key, IvParameterSpec iv) throws Exception {
        this.key = key;
        this.iv = iv;

    }

    public String decrypt(String cipherText) throws Exception {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
