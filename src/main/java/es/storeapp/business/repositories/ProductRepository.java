package es.storeapp.business.repositories;

import es.storeapp.business.entities.Product;

import java.text.MessageFormat;
import java.util.List;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends AbstractRepository<Product> {
    
    private static final String FIND_BY_CATEGORY_QUERY = 
            "SELECT p FROM Product p WHERE p.category.name = ?1 ORDER BY p.{0}";
    
    @SuppressWarnings("unchecked")
    public List<Product> findByCategory(String category, String orderColumn) {
        Query query = entityManager.createQuery(MessageFormat.format(FIND_BY_CATEGORY_QUERY, orderColumn));
        query.setParameter(1,category);
        return (List<Product>) query.getResultList();
    }
    
}
