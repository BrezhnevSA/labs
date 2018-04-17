package restS;

public class IncorrectUsernameException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;

    public IncorrectUsernameException(String message) {  
		super(message); 
    }
}