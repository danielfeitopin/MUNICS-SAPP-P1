package es.storeapp.business.exceptions;

public class AuthenticationException extends Exception {
    
    private static final long serialVersionUID = 9047626511480506926L;

    public AuthenticationException(String message) {
        super(message);
    }
    
}
