import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Check password validity and strength according to the given requirements.
 * @author nanaa
 */

public class PasswordCheckerUtility{

	
	
	/**
	 * Returns an arraylist of invalid passwords (weak passwords are not considered invalid)
	 * @param passwords arraylist of passwords to check for validity
	 * @return reasons - arraylist of invalid passwords. It will not return weak passwords.
	 */
	
	
	public static ArrayList<String> invalidPasswords(ArrayList<String> passwords){
		
		ArrayList<String> reasons = new ArrayList<String>();
		
		for(String testPass : passwords) {
			try {
				isValidPassword(testPass);
			} catch (Exception e) {
				reasons.add(testPass + " " + e.getMessage());
			}
		}
		
		return reasons;
		
		
	}
			
	
	
	
	
	
	/**
	 * Returns true if valid password. Throws exceptions for invalid passwords. Valid if password is greater than or equal to 6 characters, has at least one digit, has at least on upper-case alphabetic character, has at least on lower-case alphabetic character, and has no more than two of the same character in a row.
	 * @param passwordString The passsword to be tested
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoDigitException thrown if no digit
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws InvalidSequenceException thrown if more than 2 of same character.
	 * @return true if valid password, set up to return false if an invalid password, but throws an exception instead.
	 */
			
	
	public static boolean isValidPassword(String passwordString) throws LengthException,
																	    NoDigitException,
																	    NoUpperAlphaException,
																	    NoLowerAlphaException,
																	    InvalidSequenceException {
		if (passwordString.length() < 6){
			throw new LengthException("The password must be at least 6 characters long.");
		}  else if (!Pattern.matches(".*[A-Z].*", passwordString)){ //using regex to check conditions
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic letter.");
		} else if (!Pattern.matches(".*[a-z].*", passwordString)){
			throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic letter.");
		} else if (!Pattern.matches(".*\\d.*", passwordString)){	
			throw new NoDigitException("The password must contain at least one digit.");
		} else if (Pattern.matches("^.*(.)\\1\\1.*$", passwordString)){
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
		} else return true;
		
		
	}
		
	
	
	
	
	/**
	 * Return true if length of password is greater or equal to 6 but less than or equal to 9
	 * @param passwordString The passsword to be tested
	 * @return true if length of password is greater than or equal to 6 but less than or equal to 9
	 */
		
	
	public static boolean isWeakPassword(String passwordString) {
		return (passwordString.length() > 5 && passwordString.length() < 10) ? true : false;
	}
	
	
	
	
}
	