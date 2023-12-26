package es.storeapp.business.utils;

import es.storeapp.business.exceptions.AuthenticationException;
import es.storeapp.business.exceptions.DuplicatedResourceException;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.exceptions.InvalidStateException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionGenerationUtils {
    
    @Autowired
    private MessageSource messageSource;
    
    public InstanceNotFoundException toInstanceNotFoundException(Long id, String type, String messageKey) 
            throws InstanceNotFoundException {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(messageKey, new Object[]{type, id}, locale);
        return new InstanceNotFoundException(id, type, message);
    }
    
    public DuplicatedResourceException toDuplicatedResourceException(String resource, String value, String messageKey) 
            throws DuplicatedResourceException {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(messageKey, new Object[]{value, resource}, locale);
        return new DuplicatedResourceException(resource, value, message);
    }

    public AuthenticationException toAuthenticationException(String messageKey, String user) 
            throws AuthenticationException {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(messageKey, new Object[] {user}, locale);
        return new AuthenticationException(message);
    }

    public InvalidStateException toInvalidStateException(String messageKey) throws InvalidStateException {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(messageKey, new Object[0], locale);
        return new InvalidStateException(message);
    }
    
}
