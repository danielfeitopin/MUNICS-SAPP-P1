package es.storeapp.business.repositories;

import es.storeapp.business.entities.Comment;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends AbstractRepository<Comment>{

    private static final String COUNT_BY_USER_AND_PRODUCT_QUERY = 
        "SELECT COUNT(*) FROM Comment c WHERE c.user.id = ?1 and c.product.id = ?2";
    
    private static final String FIND_BY_USER_AND_PRODUCT_QUERY = 
        "SELECT c FROM Comment c WHERE c.user.id = ?1 and c.product.id = ?2";
    
    public Integer countByUserAndProduct(Long userId, Long productId) {
        Query query = entityManager.createQuery(COUNT_BY_USER_AND_PRODUCT_QUERY);
        query.setParameter(1,userId);
        query.setParameter(2,productId);
        return (Integer) query.getSingleResult();
    }
    
    public Comment findByUserAndProduct(Long userId, Long productId) {
        Query query = entityManager.createQuery(FIND_BY_USER_AND_PRODUCT_QUERY);
        query.setParameter(1,userId);
        query.setParameter(2,productId);
        return (Comment) query.getSingleResult();
    }
    
}
