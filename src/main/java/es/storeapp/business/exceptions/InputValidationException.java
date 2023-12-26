package es.storeapp.business.exceptions;

public class InputValidationException extends Exception {

    private static final long serialVersionUID = -6476895393943904784L;

    public InputValidationException(String message) {
        super(message);
    }    
    
}
