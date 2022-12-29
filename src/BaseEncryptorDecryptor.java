import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

public class BaseEncryptorDecryptor {

    protected Key key;

    protected String algorithm = "AES/CBC/PKCS5Padding";

    protected IvParameterSpec iv;

}
