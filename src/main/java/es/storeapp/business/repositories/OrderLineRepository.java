package es.storeapp.business.repositories;

import es.storeapp.business.entities.*;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderLineRepository extends AbstractRepository<OrderLine>{

    private static final String FIND_BY_USER_AND_PRODUCT_QUERY = 
            "SELECT COUNT(*) FROM OrderLine o WHERE " +
            "o.order.state = es.storeapp.business.entities.OrderState.COMPLETED " + 
            "AND o.order.user.id = ?1 AND o.product.id = ?2";

    public boolean findIfUserBuyProduct(Long userId, Long productId) {
        Query query = entityManager.createQuery(FIND_BY_USER_AND_PRODUCT_QUERY);
        query.setParameter(1,userId);
        query.setParameter(2,productId);
        return ((Long) query.getSingleResult()) > 0;
    }
    
}
