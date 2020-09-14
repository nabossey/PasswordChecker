/**
 * Thrown if no digit is found in a password.
 * @author nanaa
 */

public class NoDigitException extends RuntimeException {

	NoDigitException(){}
	
	NoDigitException(String s){
		super(s);
	}

}
