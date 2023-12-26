package es.storeapp.business.exceptions;

public class InstanceNotFoundException extends Exception {

    private static final long serialVersionUID = -4208426212843868046L;

    private final Long id;
    private final String type;

    public InstanceNotFoundException(Long id, String type, String message) {
        super(message);
        this.id = id;
        this.type = type;
    }

    public Object getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    
}
