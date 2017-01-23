package util;

public class Exception extends Exception {
    private static final long serialVersionUID = -6679173993840660479L;
    
    public Exception(Throwable e) {
	super(e);
    }

    public Exception(String message) {
	super(message);
    }
    
    public Exception(String message, Throwable e) {
	super(message, e);
    }
}
