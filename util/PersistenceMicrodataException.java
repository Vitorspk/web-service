package util;

public class PersistenceException extends Exception {
    private static final long serialVersionUID = 8664535931565819348L;
    
    public PersistenceException(Throwable e) {
	super(e);
    }

    public PersistenceException(String message) {
	super(message);
    }
    
    public PersistenceException(String message, Throwable e) {
	super(message, e);
    }

    public static Throwable getCause(Throwable e) {
	Throwable cause = null;
	Throwable result = e;

	while ((cause = result.getCause()) != null && (cause != result)) {
	    result = cause;
	}
	return result;
    }
}
