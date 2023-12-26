package es.storeapp.business.repositories;

import es.storeapp.business.entities.Category;
import java.util.List;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractRepository<Category> {

    private static final String FIND_HIGHLIGHTED_QUERY = "SELECT c FROM Category c WHERE c.highlighted = true";

    @SuppressWarnings("unchecked")
    public List<Category> findHighlighted() {
        Query query = entityManager.createQuery(FIND_HIGHLIGHTED_QUERY);
        return (List<Category>) query.getResultList();
    }
    
}
