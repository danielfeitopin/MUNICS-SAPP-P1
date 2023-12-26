package es.storeapp.web.controller;

import es.storeapp.business.entities.Category;
import es.storeapp.business.entities.Product;
import es.storeapp.business.entities.User;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.services.OrderService;
import es.storeapp.business.services.ProductService;
import es.storeapp.common.Constants;
import es.storeapp.web.exceptions.ErrorHandlingUtils;
import es.storeapp.web.forms.ProductSearchForm;
import java.util.List;
import java.util.Locale;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ProductController {
        
    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(ProductController.class);
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    ErrorHandlingUtils errorHandlingUtils;
    
    @GetMapping(value = {Constants.PRODUCTS_ENDPOINT})
    public String doGetProductsPage(@ModelAttribute ProductSearchForm searchForm, Model model) {
        List<Product> products = productService.findProducts(searchForm.getCategory());
        List<Category> categories = productService.findAllCategories();
        model.addAttribute(Constants.PRODUCTS, products);
        model.addAttribute(Constants.CATEGORIES, categories);
        return Constants.PRODUCTS_PAGE;
    }
    
    @GetMapping(value = {Constants.PRODUCT_ENDPOINT})
    public String doGetProductPage(@PathVariable() Long id, 
                                   @SessionAttribute(value= Constants.USER_SESSION, required = false) User user,
                                   Model model, 
                                   Locale locale) {
        Product product;
        try {
            product = productService.findProductById(id);
            model.addAttribute(Constants.PRODUCT, product);
            if(user != null) {
                model.addAttribute(Constants.BUY_BY_USER, orderService.findIfUserBuyProduct(user.getUserId(), id));
            } else {
                model.addAttribute(Constants.BUY_BY_USER, false);
            }
        } catch (InstanceNotFoundException ex) {
            if(logger.isErrorEnabled()) {
                logger.error(ex.getMessage(), ex);
            }
            return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        }
        
        return Constants.PRODUCT_PAGE;
    }
   
}
