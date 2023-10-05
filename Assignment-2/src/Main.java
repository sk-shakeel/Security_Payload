import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Main {
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        int saltLength = 16;

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLength];
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();


        byte[] combined = new byte[saltLength + hash.length];
        System.arraycopy(salt, 0, combined, 0, saltLength);
        System.arraycopy(hash, 0, combined, saltLength, hash.length);
        return Base64.getEncoder().encodeToString(combined);
    }

    public static boolean verifyPassword(String providedPassword, String storedSaltedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltedHash = Base64.getDecoder().decode(storedSaltedHash);
        int saltLength = 16;

        byte[] salt = new byte[saltLength];
        System.arraycopy(saltedHash, 0, salt, 0, saltLength);

        PBEKeySpec spec = new PBEKeySpec(providedPassword.toCharArray(), salt, 10000, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        if (hash.length != saltedHash.length - saltLength) {
            return false;
        }

        int diff = 0;
        for (int i = 0; i < hash.length; i++) {
            diff |= hash[i] ^ saltedHash[saltLength + i];
        }

        return diff == 0;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "mySecurePassword";

        String saltedHash = hashPassword(password);
        System.out.println("Salted Hash: " + saltedHash);

        boolean passwordMatch = verifyPassword("mySecurePassword", saltedHash);
        System.out.println("Password Match: " + passwordMatch);
    }
}