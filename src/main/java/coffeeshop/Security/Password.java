package coffeeshop.Security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Password {
    public static String getHashedPw(String pw) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return getSecurePassword(pw, getSalt());
    }

    private static String getSecurePassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return hash.toString();
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
//     [B@29ca901e
// [B@5649fd9b
// [B@53f65459

// [B@29ca901e
// [B@5649fd9b
// [B@3b088d51

// [B@29ca901e
// [B@5649fd9b
// [B@1b68ddbd





    public static void tester() throws NoSuchAlgorithmException, InvalidKeySpecException {
            System.out.println(getSalt());
            System.out.println(getSecurePassword("doodles", getSalt()));
    }
}
