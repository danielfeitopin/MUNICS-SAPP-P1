package es.storeapp.business.repositories;

import es.storeapp.business.entities.Order;
import java.util.List;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends AbstractRepository<Order> {
    private static final String FIND_BY_USER_QUERY = 
            "SELECT o FROM Order o WHERE o.user.id = ?1 ORDER BY o.timestamp DESC";
        
    @SuppressWarnings("unchecked")
    public List<Order> findByUserId(Long userId) {
        Query query = entityManager.createQuery(FIND_BY_USER_QUERY);
        query.setParameter(1,userId);
        return (List<Order>) query.getResultList();
    }
   
}
