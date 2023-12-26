package es.storeapp.web.controller;

import es.storeapp.business.entities.Category;
import es.storeapp.business.services.ProductService;
import es.storeapp.common.Constants;
import java.text.MessageFormat;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(HomeController.class);
    
    @Autowired
    private ProductService productService;
    
    @GetMapping(value = {Constants.ROOT_ENDPOINT})
    public String doGetHome(Model model, HttpServletResponse response) {
        List<Category> categories = productService.findHighlightedCategories();
        model.addAttribute(Constants.CATEGORIES, categories);
        if(logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Home categories: {0}", categories));
        }
        return Constants.HOME_PAGE;
    }
}
