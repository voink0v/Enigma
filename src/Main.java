import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        SecretKey keyFromPassword = getKeyFromPassword("Пароль!", "Соль!!!");
        IvParameterSpec iv = generateIv();

        Encryptor encryptor = new Encryptor(keyFromPassword, iv);
        Decryptor decryptor = new Decryptor(keyFromPassword, iv);

        while (true) {
            System.out.println("Введите сообщение для шифрования...");
            Message msg = getInput();
            if ("end".equals(msg.getText())) {
                break;
            }
            System.out.println("начинаем кодировать сообщение");

            String encrypted = encryptor.encrypt(msg.toString());
            System.out.println("Зашифрованная строка: " + encrypted);

            String decrypted = decryptor.decrypt(encrypted);
            System.out.println("Расшифрованная строка: " + decrypted);
        }
    }

    public static Message getInput() {
        String input = scanner.nextLine();
        Message message = new Message(input);
        return message;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

}

