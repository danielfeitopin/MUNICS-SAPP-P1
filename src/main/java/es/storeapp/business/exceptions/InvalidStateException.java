package es.storeapp.business.exceptions;

public class InvalidStateException extends Exception {

    private static final long serialVersionUID = 3026551774263871416L;

    public InvalidStateException(String message) {
        super(message);
    }
    
}
