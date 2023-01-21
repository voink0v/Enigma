import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class Main {

    static Cryptor cryptor;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            beginEncryption();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("Возникло непредвиденное исключение: " + e.getMessage());
        }
    }

    public static void beginEncryption() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        System.out.println("Введите пароль для шифрования");
        String password = scanner.nextLine();
        System.out.println("Введите соль для шифрования");
        String salt = scanner.nextLine();
        SecretKey keyFromPassword = getKeyFromPassword(password, salt);
        IvParameterSpec iv = generateIv();
        cryptor = new Cryptor(keyFromPassword, iv);

        while (true) {
            System.out.println("Введите сообщение для шифрования...");
            Message msg = getInput();
            if ("end".equals(msg.getText())) {
                break;
            }

            if (msg.getText() == null || msg.getText().length() == 0) {
                continue;
            }
            String result = "";
            try {
                result = cryptor.decrypt(msg.getText());
                System.out.println("Упс... Строка уже зашифрована, сейчас покажем её расшифрованный вариант");
            } catch (IllegalArgumentException e) {
                System.out.println("Строка не зашифрована. Начинаем кодировать сообщение");

                String encrypted = cryptor.encrypt(msg.toString());
                System.out.println("Зашифрованная строка: " + encrypted);

                result = cryptor.decrypt(encrypted);
            }

            System.out.println("Результат дешифрования: " + result);
        }
    }

    public static Message getInput() {
        String input = scanner.nextLine();
        return new Message(input);
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }


}


