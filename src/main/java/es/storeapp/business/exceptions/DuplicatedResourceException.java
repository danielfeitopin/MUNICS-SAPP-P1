package es.storeapp.business.exceptions;

public class DuplicatedResourceException extends Exception {
    
    private static final long serialVersionUID = 6896927410877749980L;

    private final String resource;
    private final String value;

    public DuplicatedResourceException(String resource, String value, String message) {
        super(message);
        this.resource = resource;
        this.value = value;
    }

    public Object getResource() {
        return resource;
    }

    public Object getValue() {
        return value;
    }
         
}
