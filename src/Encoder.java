import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Encoder extends BaseEncoderDecoder {


    public Encoder(Key key) throws Exception {
        this.key = key;
        this.cipher = Cipher.getInstance("RSA");
        this.cipher.init(Cipher.ENCRYPT_MODE, key);

    }

    public byte[] encode(Message message) throws IllegalBlockSizeException, BadPaddingException {
        String text = message.toString();
        byte[] data = text.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(data);
    }


}
