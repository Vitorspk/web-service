package br.com.microdatasistemas.simintegrationws.util;

public class MicrodataException extends Exception {
    private static final long serialVersionUID = -6679173993840660479L;
    
    public MicrodataException(Throwable e) {
	super(e);
    }

    public MicrodataException(String message) {
	super(message);
    }
    
    public MicrodataException(String message, Throwable e) {
	super(message, e);
    }
}