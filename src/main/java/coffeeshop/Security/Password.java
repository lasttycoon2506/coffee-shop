package coffeeshop.Security;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class Password {
    public static String getHashedPw(String pw) {
        return getSecurePassword(pw);
    }

    private static String getSecurePassword(String pw) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, pw.toCharArray());
        return bcryptHashString;
    }
}
