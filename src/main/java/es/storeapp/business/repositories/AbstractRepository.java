package es.storeapp.business.repositories;

import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.utils.ExceptionGenerationUtils;
import es.storeapp.common.Constants;
import java.text.MessageFormat;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

public abstract class AbstractRepository<T> {

    protected final EscapingLoggerWrapper logger;
    
    private static final String FIND_ALL_QUERY = "SELECT t FROM {0} t";
    private static final String FIND_ALL_ORDERED_QUERY = "SELECT t FROM {0} t ORDER BY t.{1}";
    private static final String FIND_BY_TEXT_ATTRIBUTE_QUERY = "SELECT t FROM {0} t WHERE t.{1} = ''{2}'' ORDER BY t.{3}";
    
    private final Class<T> genericType;

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    ExceptionGenerationUtils exceptionGenerationUtils;
    
    public AbstractRepository() {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepository.class);
        this.logger = new EscapingLoggerWrapper(this.genericType);
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }
    
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public T findById(Long id) throws InstanceNotFoundException {
        try{
            T t = entityManager.find(genericType, id);
            if(t == null) {
                throw new NoResultException(Long.toString(id));
            }
            return t;
        } catch(NoResultException e) {
            logger.error(e.getMessage(), e);
            throw exceptionGenerationUtils.toInstanceNotFoundException(id, genericType.getSimpleName(), 
                    Constants.INSTANCE_NOT_FOUND_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query query = entityManager.createQuery(MessageFormat.format(FIND_ALL_QUERY, 
                genericType.getSimpleName()));
        return (List<T>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> findAll(String orderColumn) {
        Query query = entityManager.createQuery(MessageFormat.format(FIND_ALL_ORDERED_QUERY, 
                genericType.getSimpleName(), orderColumn));
        return (List<T>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> findByStringAttribute(String attribute, String value, String orderColumn) {
        Query query = entityManager.createQuery(MessageFormat.format(FIND_BY_TEXT_ATTRIBUTE_QUERY, 
                genericType.getSimpleName(), attribute, value, orderColumn));
        return (List<T>) query.getResultList();
    }
}
