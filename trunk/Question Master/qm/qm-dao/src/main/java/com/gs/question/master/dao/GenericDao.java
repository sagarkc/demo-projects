package com.gs.question.master.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.criterion.DetachedCriteria;


public interface GenericDao<T, I extends Serializable> {
	
	public void setEntityManager(EntityManager enmgr);

	/**
	 * This method will get the entity into the DB if its a new entity or an
	 * existing detached entity.
	 * 
	 * @param entity
	 * @return stored entity
	 */
	public T store(T entity);

	/**
	 * This method will get the entity into the DB if its a new entity or an
	 * existing detached entity.
	 * 
	 * @param entity
	 * @return collection of stored entities.
	 */
	public Collection<T> store(Collection<T> entities);

	/**
	 * Persist the entity. persist will take the entity and put it into the db.
	 * 
	 * @param entity
	 * @return void
	 */
	public void persist(T entity);

	/**
	 * Persist the entities. persist will take the entity and put it into the
	 * DB.
	 * 
	 * @param entity
	 */
	public void persist(Collection<T> entities);

	/**
	 * Merge the entity, returning (a potentially different object) the
	 * persisted entity. merge will take an exiting 'detached' entity and merge
	 * its properties onto an existing entity. The entity with the merged state
	 * is returned.
	 * 
	 * @param entity
	 * @return merged entity
	 */
	public T merge(T entity);

	/**
	 * Merge the collection of entities merge will take an exiting 'detached'
	 * entity and merge its properties onto an existing entity. The entity with
	 * the merged state is returned.
	 * 
	 * @param entities
	 *            collection of entities
	 * @return a collection of managed entities
	 * 
	 */
	public Collection<T> merge(Collection<T> entities);

	/**
	 * Merge a related entity into the persistent context. Management of this
	 * related entity may be required to detect changes to bidirectional
	 * relationships.
	 * 
	 * @param relatedEntity
	 * @return a managed instance of the relatedEntity argument
	 */
	public <RE> RE mergeRelated(RE relatedEntity);

	/**
	 * Merge the collection of related entities, returning the persisted
	 * entities (potentially different instances).merge will take an exiting
	 * 'detached' entity and merge its properties onto an existing entity. The
	 * entity with the merged state is returned.
	 * 
	 * @param entities
	 *            collection of entity objects
	 * @return a collection of managed entities
	 */
	public <RE> Collection<RE> mergeRelated(Collection<RE> entities);

	/**
	 * Remove an entity from persistent storage in the database. If the entity
	 * is not in the 'managed' state, it is merged into the persistent context
	 * then removed.
	 * 
	 * @param entity
	 *            The Primary Key of the object to delete.
	 */
	public void delete(T entity);

	/**
	 * Remove an object from persistent storage in the database. Since this uses
	 * the PK to do the delete there is a chance that the entity manager could
	 * be left in an inconsistent state, if you delete the object with id 1 but
	 * that object is still in the entity managers cache.
	 * 
	 * You can do two things, put all your PK deletes together and then call
	 * flushAndClear when done, or you can just call the delete method with the
	 * entity which will not suffer from this problem.
	 * 
	 * @see delete(T entity)
	 * @param id
	 *            The Primary Key of the object to delete.
	 */
	public void deleteById(I id);

	/**
	 * Remove a collection of entities from persistent storage in the database.
	 * 
	 * @param entity
	 *            The Primary Key of the object to delete.
	 */
	public void delete(Collection<T> entities);

	/**
	 * get the reference entity of having PK as id
	 */
	public T getReference(I id);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 * 
	 * @param id
	 *            The Primary Key of the object to get.
	 * @return Type
	 * @throws UnsupportedOperationException
	 *             if entity type T is null.
	 */
	public T read(I id);

	/**
	 * Retrieves an entity that was previously persisted to the database using
	 * the parameter as the primary key, and maintain an exclusive lock on that
	 * entity's database row(s) until the transaction is committed. Note that
	 * this method must be executed with the bounds of a transaction.
	 * 
	 * @param id
	 *            The Primary Key of the entity to retrieve.
	 * @return Type
	 */
	public T readExclusive(I id);

	/**
	 * Refresh an entity that may have changed in another thread/transaction. If
	 * the entity is not in the 'managed' state, it is first merged into the
	 * persistent context, then refreshed.
	 * 
	 * @param transientObject
	 *            The Object to refresh.
	 * @throws UnsupportedOperationException
	 *             if entity type T is null.
	 */
	public T refresh(T transientObject);

	/**
	 * Refresh an entity that may have changed in another thread/transaction. If
	 * the entity is not in the 'managed' state, it is located using
	 * EntityManager.find() then refreshed.
	 * 
	 * @param transientObject
	 *            The Object to refresh.
	 * @throws UnsupportedOperationException
	 *             if entity type T is null.
	 */
	public T refreshById(I id);

	/**
	 * Refresh a collection of entities that may have changed in another
	 * thread/transaction.
	 * 
	 * @param transientObject
	 *            The Object to refresh.
	 */
	public Collection<T> refresh(Collection<T> entities);

	/**
	 * Write anything to DB that is pending operation and clear it.
	 */
	public void flushAndClear();

	/**
	 * return the all entities
	 * 
	 */

	public List<T> getAll();

	/**
	 * return the all entities
	 * 
	 */

	public List<T> getAll(String sortByColumn, boolean ascendingOrder);

	/**
	 * Allows getting an object using a map of the field and values
	 * 
	 * @param propertyValues
	 *            properties of VO to use as filters, values matching properties
	 * @return List of requested objects.
	 */
	public List<T> find(Map<String, Object> propertyValues);

	/**
	 * Allows getting an object using a map of the field and values
	 * 
	 * @param propertyNames
	 *            Names of the fields on which to search.
	 * @param values
	 *            Values of the fields on which this is searching.
	 * @return List of requested objects.
	 */
	public List<T> find(String[] propertyNames, Object[] values);

	/**
	 * Does a query such as select from entity class where field=value orderBy
	 * field.
	 * 
	 * @param propertyValues
	 *            properties of VO to use as filters
	 * @param orderBy
	 *            fields to order by in ascending order
	 * @return List of requested objects.
	 */
	public List<T> find(Map<String, Object> propertyValues, String[] orderBy);

	/**
	 * Method takes a class object and string field, and value to get a list of
	 * objects. This is like a select "where something = value"
	 * 
	 * @param property
	 *            field in entity
	 * @param value
	 *            value to search on
	 * @return list of the annotated objects
	 */
	public List<T> find(String property, Object value);

	/**
	 * Execute a query based on the given criteria object
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * Execute a named query binding a number of values to "?"
	 * 
	 * @param queryName
	 * @param values
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName, Object... values);

	public <F> List<F> findByNamedQuery(Class<F> c, String queryName,
			Object... values);

	public <F> List<F> findByNamedQuery(Class<F> c, String queryName);

	public T executeNamedQueryForSingleResult(String queryName,
			Object... values);

	public <F> F executeNamedQueryForSingleResult(Class<F> c, String queryName,
			Object... values);

	public <F> F executeNamedQueryForSingleResult(Class<F> c, String queryName);

	public Integer executeUpdateNamedQuery(String queryName, Object... values);

	public Integer executeUpdateNamedQuery(String queryName);

	/**
	 * Clear the persistence context, causing all managed entities to become
	 * detached
	 */
	public void clear();

	public Integer maxCount(String column, String entityName, String condition);

	/**
	 * Synchronize the persistence context to the underlying database.
	 */
	public void flush();

	public <F> List<F> findByCriteria(Class<F> c,
			DetachedCriteria detachedCriteria);

	public Integer delete(String query);

	public <F> List<F> findByCriteria(Class<F> clazz,
			DetachedCriteria detachedCriteria, int firstResult, int maxResults);
	
	
    /**
     * Get the JPA CriteriaBuilder
     * @return
     */
    public CriteriaBuilder getCriteriaBuilder();
    
    /**
     * Execute a native query.
     * @param queryStr
     * @param resultSetType
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List executeNativeQuery(String queryStr, String resultSetType);
}
