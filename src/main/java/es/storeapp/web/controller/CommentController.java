package es.storeapp.web.controller;

import es.storeapp.business.entities.Comment;
import es.storeapp.business.entities.User;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.services.ProductService;
import es.storeapp.common.Constants;
import es.storeapp.web.exceptions.ErrorHandlingUtils;
import es.storeapp.web.forms.CommentForm;
import java.text.MessageFormat;
import java.util.Locale;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(CommentController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    ErrorHandlingUtils errorHandlingUtils;
    
    @GetMapping(value = {Constants.COMMENT_PRODUCT_ENDPOINT})
    public String doGetCommentPage(@PathVariable() Long id, 
                                   @SessionAttribute(Constants.USER_SESSION) User user, 
                                   Model model, 
                                   Locale locale) {
        CommentForm commentForm = new CommentForm();
        commentForm.setProductId(id);
        model.addAttribute(Constants.COMMENT_FORM, commentForm);
        try {
            model.addAttribute(Constants.PRODUCT, productService.findProductById(id));
            Comment comment = productService.findCommentByUserAndProduct(user, id);
            if(comment != null) {
                commentForm.setRating(comment.getRating());
                commentForm.setText(comment.getText());
                if(logger.isDebugEnabled()) {
                    logger.debug(MessageFormat.format("Loading previous comment {0}", commentForm));
                }
            }
        } catch (InstanceNotFoundException ex) {
            return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        }
        return Constants.COMMENT_PAGE;
    }
   
    @PostMapping(Constants.COMMENT_PRODUCT_ENDPOINT)
    public String doCreateComment(@SessionAttribute(Constants.USER_SESSION) User user,
                                  @Valid @ModelAttribute(Constants.COMMENT_FORM) CommentForm commentForm,
                                  HttpSession session, 
                                  RedirectAttributes redirectAttributes,
                                  Locale locale, 
                                  Model model) {
        try {
            productService.comment(user, commentForm.getProductId(), commentForm.getText(), commentForm.getRating());
            String message = messageSource.getMessage(Constants.PRODUCT_COMMENT_CREATED, new Object[0], locale);
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, message);
            return Constants.SEND_REDIRECT + MessageFormat.format(Constants.PRODUCT_TEMPLATE,
                commentForm.getProductId());
        } catch (InstanceNotFoundException ex) {
           return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        }
    }
    
}
