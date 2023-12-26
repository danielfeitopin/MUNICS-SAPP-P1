package es.storeapp.business.services;

import es.storeapp.business.entities.Category;
import es.storeapp.business.entities.Comment;
import es.storeapp.business.entities.Product;
import es.storeapp.business.entities.User;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.repositories.CategoryRepository;
import es.storeapp.business.repositories.CommentRepository;
import es.storeapp.business.repositories.ProductRepository;
import es.storeapp.business.utils.ExceptionGenerationUtils;
import es.storeapp.common.ConfigurationParameters;
import es.storeapp.common.Constants;
import java.text.MessageFormat;
import java.util.List;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(ProductService.class);

    @Autowired
    ConfigurationParameters configurationParameters;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository rateRepository;

    @Autowired
    ExceptionGenerationUtils exceptionGenerationUtils;
    
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll(Constants.PRICE_FIELD);
    }

    @Transactional(readOnly = true)
    public List<Product> findProducts(String category) {
        if (category == null || category.length() == 0) {
            return productRepository.findAll(Constants.PRICE_FIELD);
        } else {
            return productRepository.findByCategory(category, Constants.PRICE_FIELD);
        }
    }

    @Transactional(readOnly = false)
    public Product findProductById(Long id) throws InstanceNotFoundException {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Category> findHighlightedCategories() {
        return categoryRepository.findHighlighted();
    }

    @Transactional()
    public Comment findCommentByUserAndProduct(User user, Long productId)
            throws InstanceNotFoundException {
        try {
            Product product = productRepository.findById(productId);
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("Searching if the user {0} has commented the product {1}", 
                    user.getEmail(), product.getName()));
            }
            return rateRepository.findByUserAndProduct(user.getUserId(), productId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional()
    public Comment comment(User user, Long productId, String text, Integer rating)
            throws InstanceNotFoundException {
        Product product = productRepository.findById(productId);
        try {
            Comment comment = rateRepository.findByUserAndProduct(user.getUserId(), product.getProductId());
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("{0} has modified his comment of the product {1}", 
                    user.getName(), product.getName()));
            }
            product.setTotalScore(product.getTotalScore() - comment.getRating() + rating);
            comment.setRating(rating);
            comment.setText(text);
            comment.setTimestamp(System.currentTimeMillis());
            productRepository.update(product);
            return rateRepository.update(comment);
        } catch (EmptyResultDataAccessException e) {
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("{0} created a comment of the product {1}", 
                    user.getName(), product.getName()));
            }
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setProduct(product);
            comment.setRating(rating);
            comment.setText(text);
            comment.setTimestamp(System.currentTimeMillis());
            product.setTotalComments(product.getTotalComments() + 1);
            product.setTotalScore(product.getTotalScore() + rating);
            productRepository.update(product);
            return rateRepository.create(comment);
        }
    }

}
