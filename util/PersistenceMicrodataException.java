package br.com.microdatasistemas.simintegrationws.util;

public class PersistenceMicrodataException extends MicrodataException {
    private static final long serialVersionUID = 8664535931565819348L;
    
    public PersistenceMicrodataException(Throwable e) {
	super(e);
    }

    public PersistenceMicrodataException(String message) {
	super(message);
    }
    
    public PersistenceMicrodataException(String message, Throwable e) {
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