package es.storeapp.business.repositories;

import es.storeapp.business.entities.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<User> {
    
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT u FROM User u WHERE u.email = ?1";
    private static final String COUNT_USER_BY_EMAIL_QUERY = "SELECT COUNT(*) FROM User u WHERE u.email = ?1";
    private static final String LOGIN_QUERY = "SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2";

    public User findByEmail(String email) {
        try {
            Query query = entityManager.createQuery(FIND_USER_BY_EMAIL_QUERY);
            query.setParameter(1,email);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public boolean existsUser(String email) {
        Query query = entityManager.createQuery(COUNT_USER_BY_EMAIL_QUERY);
        query.setParameter(1,email);
        return ((Long) query.getSingleResult() > 0);
    }
    
    public User findByEmailAndPassword(String email, String password) {
        try {
            Query query = entityManager.createQuery(LOGIN_QUERY);
            query.setParameter(1,email);
            query.setParameter(2,password);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
