import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static final String AES
            = "AES";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {


        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(4096);
        KeyPair keyPair = keyGenerator.generateKeyPair();


        Decoder decoder = new Decoder(keyPair.getPrivate());
        Encoder encoder = new Encoder(keyPair.getPublic());

        while (true) {
            Message msg = getInput();
            if ("end".equals(msg.getText())) {
                break;
            }
            System.out.println("начинаем кодировать сообщение");

            byte encoded[] = encoder.encode(msg);

            System.out.println("сообщение закодировано, результат: " + new String(encoded, StandardCharsets.UTF_8));

            byte decoded[] = decoder.decode(encoded);
            System.out.println("сообщение расшифровано, результат: " + new String(decoded, StandardCharsets.UTF_8));


        }


    }

    public static Message getInput() {
        String input = scanner.nextLine();
        Message message = new Message(input);
        return message;
    }


    public static SecretKey createAESKey() throws Exception {


        SecureRandom securerandom = new SecureRandom();


        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);

        // Initializing the KeyGenerator
        // with 256 bits.
        keygenerator.init(256, securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
    }


}

