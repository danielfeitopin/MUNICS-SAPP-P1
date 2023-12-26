package es.storeapp.web.exceptions;

import es.storeapp.business.exceptions.AuthenticationException;
import es.storeapp.business.exceptions.DuplicatedResourceException;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.exceptions.InvalidStateException;
import es.storeapp.common.Constants;
import java.util.Locale;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class ErrorHandlingUtils {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(ErrorHandlingUtils.class);

    @Autowired
    private MessageSource messageSource;
    
    public String handleInvalidFormError(BindingResult result, String template, 
                                         Model model, Locale locale) {
        if(logger.isErrorEnabled()) {
            logger.error(result.toString());
        }
        String message = messageSource.getMessage(template, new Object[0], locale);
        model.addAttribute(Constants.ERROR_MESSAGE, message);
        return Constants.ERROR_PAGE;
    }
    
    public String handleInstanceNotFoundException(InstanceNotFoundException e, Model model, Locale locale) {
        logger.error(e.getMessage(), e);
        model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        model.addAttribute(Constants.EXCEPTION, e);
        return Constants.ERROR_PAGE;
    }
    
    public String handleDuplicatedResourceException(DuplicatedResourceException e, String targetPage,
                                                    Model model, Locale locale) {
        logger.error(e.getMessage(), e);
        model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        model.addAttribute(Constants.EXCEPTION, e);
        return targetPage;
    }

    public String handleAuthenticationException(AuthenticationException e, String user, String targetPage,
                                                Model model, Locale locale) {
        logger.error(e.getMessage(), e);
        model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        model.addAttribute(Constants.EXCEPTION, e);
        return targetPage;
    }
    
    public String handleInvalidStateException(InvalidStateException e, Model model, Locale locale) {
        logger.error(e.getMessage(), e);
        model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        model.addAttribute(Constants.EXCEPTION, e);
        return Constants.ERROR_PAGE;
    }
    
    public String handleUnexpectedException(Exception e, Model model) {
        logger.error(e.getMessage(), e);
        model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        model.addAttribute(Constants.EXCEPTION, e);
        return Constants.ERROR_PAGE;
    }
}
