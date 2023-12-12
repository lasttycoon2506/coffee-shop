package coffeeshop.Security;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Login {
	public static boolean loginCheck(String originalPw, String hashedPw){
		// boolean matched = BCrypt.checkpw(originalPw, hashedPw);
		// return matched;
		return false;
	}
}


