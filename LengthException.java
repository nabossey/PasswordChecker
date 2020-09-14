/**
 * Thrown if password length is less than 6 characters.
 * @author nanaa
 */

public class LengthException extends RuntimeException {

	LengthException(){}
	
	LengthException(String s){
		super(s);
	}

}