package br.com.microdatasistemas.simintegrationws.util;

public class ValidationException extends MicrodataException {
    private static final long serialVersionUID = 5429943754524887878L;
    
    public ValidationException(Throwable e) {
	super(e);
    }

    public ValidationException(String message) {
	super(message);
    }
    
    public ValidationException(String message, Throwable e) {
	super(message, e);
    }
}