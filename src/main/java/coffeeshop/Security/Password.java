package coffeeshop.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import at.favre.lib.crypto.bcrypt.BCrypt;


public class Password {
    public static String getHashedPw(String pw) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return getSecurePassword(pw);
    }

    private static String getSecurePassword(String pw) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, pw.toCharArray());
        System.out.println(bcryptHashString);
        return null;
        // $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
        // BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        // result.verified == true
    }

    public static void tester(){
        getSecurePassword("password");
    }
}
