/**
 * Thrown if more than 2 of same character in sequence are detected while checking a password.
 * @author nanaa
 */

public class InvalidSequenceException extends RuntimeException {

	InvalidSequenceException(){}
	
	InvalidSequenceException(String s){
		super(s);
	}

}