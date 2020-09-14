/**
 * Thrown if the two entered passwords are not equivalent.
 * @author nanaa
 */

public class UnmatchedException extends RuntimeException {

	UnmatchedException(){}
	
	UnmatchedException(String s){
		super(s);
	}

}