package es.storeapp.business.exceptions;

public class ServiceException extends Exception {
    
    private static final long serialVersionUID = -5772226522820952867L;

    public ServiceException(String message) {
        super(message);
    }
        
}
