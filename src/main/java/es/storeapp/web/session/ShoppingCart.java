package es.storeapp.web.session;

import es.storeapp.business.entities.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 8032734613287752106L; 
    
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }
    
    public boolean contains(Long id) {
        return products.stream().anyMatch(product -> (product.getProductId().equals(id)));
    }
    
    public int getTotalPrice() {
        int totalPrice = 0;
        return products.stream().map(product -> product.getPrice()).reduce(totalPrice, Integer::sum);
    }
    
    public void clear() {
        products.clear();
    }
    
}
