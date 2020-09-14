/**
 * Thrown if no uppercase alphabetic character is found in a password.
 * @author nanaa
 */

public class NoUpperAlphaException extends RuntimeException {

	NoUpperAlphaException(){}
	
	NoUpperAlphaException(String s){
		super(s);
	}

}