import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.Key;

public class Decoder extends BaseEncoderDecoder {


    public Decoder(Key key) throws Exception {
        this.key = key;
        this.cipher = Cipher.getInstance("RSA");
        this.cipher.init(Cipher.ENCRYPT_MODE, key);

    }

    public byte[] decode(byte[] data) throws IllegalBlockSizeException, BadPaddingException {


        return cipher.doFinal(data);
    }
}
