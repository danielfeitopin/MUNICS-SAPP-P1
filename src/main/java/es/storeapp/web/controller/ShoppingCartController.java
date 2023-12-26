package es.storeapp.web.controller;

import es.storeapp.business.entities.Product;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.services.ProductService;
import es.storeapp.common.Constants;
import es.storeapp.web.exceptions.ErrorHandlingUtils;
import es.storeapp.web.session.ShoppingCart;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShoppingCartController {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(ShoppingCartController.class);
    
    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    ErrorHandlingUtils exceptionHandlingUtils;
    
    @GetMapping(value = {Constants.CART_ENDPOINT})
    public String doGetCartPage(@SessionAttribute(Constants.SHOPPING_CART_SESSION) ShoppingCart shoppingCart, 
                                Model model, 
                                Locale locale) {
        return Constants.SHOPPING_CART_PAGE;
    }
    
    @PostMapping(value = {Constants.CART_ADD_PRODUCT_ENDPOINT})
    public String doAddProductToCart(@PathVariable() Long id, 
                                     @SessionAttribute(Constants.SHOPPING_CART_SESSION) ShoppingCart shoppingCart,
                                     RedirectAttributes redirectAttributes,
                                     Model model, 
                                     Locale locale) {
        Product product;
        try {
            product = productService.findProductById(id);
            List<Product> products = shoppingCart.getProducts();
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("Adding product {0} to shopping cart", id));
            }
            for (Product p : products) {
                if (p.getProductId().equals(id)) {
                    String message = messageSource.getMessage(Constants.PRODUCT_ALREADY_IN_SHOPPING_CART,
                        new Object[]{p.getName()}, locale);
                    redirectAttributes.addFlashAttribute(Constants.ERROR_MESSAGE, message);
                    return Constants.SEND_REDIRECT + Constants.CART_ENDPOINT;
                }
            }
            String message = messageSource.getMessage(Constants.PRODUCT_ADDED_TO_THE_SHOPPING_CART,
                new Object[]{product.getName()}, locale);
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, message);
            products.add(product);
        } catch (InstanceNotFoundException ex) {
            return exceptionHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        }

        return Constants.SEND_REDIRECT + Constants.CART_ENDPOINT;
    }

    @PostMapping(value = {Constants.CART_REMOVE_PRODUCT_ENDPOINT})
    public String doRemoveProductFromCart(@PathVariable() Long id,
                                          @SessionAttribute(Constants.SHOPPING_CART_SESSION) ShoppingCart shoppingCart,
                                          RedirectAttributes redirectAttributes,
                                          Model model, 
                                          Locale locale ) {
        Product product;
        try {
            product = productService.findProductById(id);
            List<Product> products = shoppingCart.getProducts();
            for (Product p : products) {
                if (p.getProductId().equals(id)) {
                    String message = messageSource.getMessage(Constants.PRODUCT_REMOVED_FROM_THE_SHOPPING_CART,
                        new Object[]{product.getName()}, locale);
                    redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, message);
                    products.remove(p);
                    return Constants.SEND_REDIRECT + Constants.CART_ENDPOINT;
                }
            }
            String message = messageSource.getMessage(Constants.PRODUCT_NOT_IN_SHOPPING_CART,
                new Object[]{product.getName()}, locale);
            redirectAttributes.addFlashAttribute(Constants.ERROR_MESSAGE, message);
        } catch (InstanceNotFoundException ex) {
            return exceptionHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        }

        return Constants.SEND_REDIRECT + Constants.CART_ENDPOINT;
    }
    
}
