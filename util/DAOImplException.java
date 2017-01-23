package util;

public class DAOImplException extends Exception {
    private static final long serialVersionUID = -5904730204388981136L;
    
    public DAOImplException(Throwable e) {
	super(e);
    }
    
    public DAOImplException(String errorDescription) {
	super(errorDescription);
    }
    
    public DAOImplException(String message, Throwable e) {
	super(message, e);
    }
}
