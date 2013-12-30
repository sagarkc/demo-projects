package com.gs.question.master.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.gs.question.master.dao.GenericDao;

public abstract class GenericDaoImpl<T, I extends Serializable> implements
		GenericDao<T, I> {

	private Class<T> entityBeanType;

	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.entityBeanType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public T store(T entity) {
		T persistedEntity = entity;
		try {
			getEntityManager().persist(entity);
		} catch (EntityExistsException e) {
			// if the entity exists then we call merge
			persistedEntity = getEntityManager().merge(entity);
		}

		return persistedEntity;
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public Collection<T> store(Collection<T> entities) {
		List<T> results = new ArrayList<T>();
		for (T entity : entities) {
			results.add(store(entity));
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public void persist(T entity) {
		getEntityManager().persist(entity);

	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public void persist(Collection<T> entities) {
		for (T entity : entities) {
			persist(entity);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public Collection<T> merge(Collection<T> entities) {
		List<T> results = new ArrayList<T>();
		for (T entity : entities) {
			results.add(merge(entity));
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public <RE> RE mergeRelated(RE entity) {
		return getEntityManager().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public <RE> Collection<RE> mergeRelated(Collection<RE> entities) {
		Collection<RE> mergedResults = new ArrayList<RE>(entities.size());
		for (RE entity : entities) {
			mergedResults.add(mergeRelated(entity));
		}
		return mergedResults;
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public void delete(T entity) {
		// TODO: this really should be using the methods on GenericDaoJpa to get
		// the PK and call delete(PK)
		T managedEntity = entity;
		if (!getEntityManager().contains(entity)) {
			managedEntity = getEntityManager().merge(entity);
		}
		getEntityManager().remove(managedEntity);
	}

	/*
	 * deleting the entity by ID(primary key)
	 */
	@Transactional
	public void deleteById(final I id) {
		getEntityManager().remove(read(id));
	}

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public void delete(Collection<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public T read(I id) {
		if (getEntityBeanType() == null) {
			throw new UnsupportedOperationException(
					"The type must be set to use this method."); //$NON-NLS-1$
		}
		return read(getEntityBeanType(), id);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T read(Class clazz, I id) {
		return (T) getEntityManager().find(clazz, id);
	}

	/**
	 * {@inheritDoc}
	 */

	public T readExclusive(I id) {
		if (getEntityBeanType() == null) {
			throw new UnsupportedOperationException(
					"The type must be set to use this method."); //$NON-NLS-1$
		}
		return readExclusive(getEntityBeanType(), id);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T readExclusive(Class clazz, I id) {
		Object entity = getEntityManager().find(clazz, id);
		getEntityManager().lock(entity, LockModeType.READ);
		return (T) entity;
	}

	/**
	 * {@inheritDoc}
	 */

	public T refresh(final T transientObject) {
		EntityManager em = getEntityManager();
		T managedEntity = null;
		if (em.contains(transientObject)) {
			managedEntity = transientObject;
		} else {
			managedEntity = em.merge(transientObject);
		}
		// now refresh the state of the managed object
		em.refresh(managedEntity);
		return managedEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	public T refreshById(final I id) {
		if (getEntityBeanType() == null) {
			throw new UnsupportedOperationException(
					"The type must be set to use this method."); //$NON-NLS-1$
		}
		EntityManager em = getEntityManager();
		T managedEntity = em.find(getEntityBeanType(), id);
		em.refresh(managedEntity);
		return managedEntity;
	}

	/**
	 * {@inheritDoc}
	 */

	public Collection<T> refresh(Collection<T> entities) {
		Collection<T> refreshedResults = new ArrayList<T>(entities.size());
		for (T entity : entities) {
			refreshedResults.add(refresh(entity));
		}
		return refreshedResults;
	}

	/**
	 * return the no of entities
	 * 
	 * @return no of entities
	 */
	public int count() {
		String entityName = getEntityBeanType().getSimpleName();
		String queryStr = "SELECT count(*) FROM " + entityName + " instance"; //$NON-NLS-1$ //$NON-NLS-2$
		Query query = getEntityManager().createQuery(queryStr);
		Long count = (Long) query.getResultList().get(0);
		return count.intValue();
	}

	/**
	 * get the reference entity of having PK as id
	 * 
	 * @param id
	 * @return
	 */
	public T getReference(I id) {

		return getEntityManager().getReference(getEntityBeanType(), id);

	}

	/**
	 * return the all entities
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String from = "from "; //$NON-NLS-1$
		Query query = this.entityManager.createQuery(from
				+ getEntityBeanType().getName());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(String sortByColumn, boolean ascendingOrder) {
		String sortType = ascendingOrder == false ? " DESC" : " ASC";
		String from = "from "; //$NON-NLS-1$
		Query query = this.entityManager.createQuery(from
				+ getEntityBeanType().getName() + " ORDER BY " + sortByColumn
				+ sortType);

		return query.getResultList();
	}

	/**
	 * Execute a query based on the given example entity object
	 * 
	 * @param exampleInstance
	 * @param excludeProperty
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		org.hibernate.Criteria crit = createHibernateCriteria();
		org.hibernate.criterion.Example example = org.hibernate.criterion.Example
				.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	/**
	 * Find the entity based on the parameters present in the map.
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedParams(String queryString, Map<String, ?> params) {

		Query queryObject = getEntityManager().createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryObject.setParameter(entry.getKey(), entry.getValue());
			}

		}
		return queryObject.getResultList();

	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(Map<String, Object> propertyValues) {

		return find(propertyValues, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(String[] propertyNames, Object[] values) {

		if (propertyNames.length != values.length) {
			String errMsg = "You are not using this API correctly. The propertynames length should always match values length."; //$NON-NLS-1$
			throw new RuntimeException(errMsg);
		}
		Map<String, Object> propertyValues = new HashMap<String, Object>(
				propertyNames.length);
		int index = 0;
		for (String propertyName : propertyNames) {
			propertyValues.put(propertyName, values[index]);
			index++;
		}
		return find(propertyValues);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(Map<String, Object> propertyValues, String[] orderBy) {

		return findByJPACriteriaBuilder(propertyValues, orderBy);

		/*
		 * Criteria crit = createHibernateCriteria(); Set<String> keys =
		 * propertyValues.keySet(); for (String key : keys) {
		 * crit.add(Restrictions.eq(key, propertyValues.get(key))); } if
		 * (orderBy != null) { for (String coulmn : orderBy) {
		 * crit.addOrder(Order.asc(coulmn)); }
		 * 
		 * } return crit.list();
		 */
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(String property, Object value) {
		HashMap<String, Object> propertyValues = new HashMap<String, Object>();
		propertyValues.put(property, value);
		return find(propertyValues);
	}

	/**
	 * Execute a named query binding a number of values to "?"
	 * 
	 * @param queryName
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName, Object... values) {
		Query queryObject = getEntityManager().createNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i + 1, values[i]);

			}
		}
		return queryObject.getResultList();

	}

	public List<T> findByNamedQuery(String queryName) {
		return findByNamedQuery(queryName, null);
	}

	@SuppressWarnings("unchecked")
	public <F> List<F> findByNamedQuery(Class<F> c, String queryName,
			Object... values) {
		Query queryObject = getEntityManager().createNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i + 1, values[i]);
			}
		}
		return queryObject.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <F> List<F> findByNamedQuery(Class<F> c, String queryName) {
		Query queryObject = getEntityManager().createNamedQuery(queryName);
		return queryObject.getResultList();
	}

	public Integer maxCount(String column, String entityName, String condition) {
		String queryStmt = "SELECT max(" + column + ") FROM " + entityName;
		if (StringUtils.isNotBlank(condition)) {
			queryStmt = queryStmt + " " + condition;
		}
		Query query = getEntityManager().createQuery(queryStmt);
		Object value = query.getSingleResult();
		return value == null ? 0 : (Integer) value;

	}

	/**
	 * Execute a query based on the given example entity object
	 * 
	 * @param criterion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(
			org.hibernate.criterion.Criterion... criterion) {
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		org.hibernate.Criteria crit = createHibernateCriteria();
		for (org.hibernate.criterion.Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	/**
	 * Execute a query based on the given criteria object
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {

		Criteria crit = detachedCriteria.getExecutableCriteria(this
				.getHibernateSession());

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public <F> List<F> findByCriteria(Class<F> clazz,
			DetachedCriteria detachedCriteria, int firstResult, int maxResults) {

		Criteria crit = detachedCriteria.getExecutableCriteria(this
				.getHibernateSession());
		crit.setFirstResult(firstResult).setMaxResults(maxResults);
		return (List<F>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public <F> List<F> findByCriteria(Class<F> c,
			DetachedCriteria detachedCriteria) {
		Criteria crit = detachedCriteria.getExecutableCriteria(this
				.getHibernateSession());
		return crit.list();
	}

	/**
	 * use full method for executing native queries.
	 * 
	 * @param queryStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> execteNativeQuery(String queryStr) {

		Query query = getEntityManager().createNativeQuery(queryStr,
				getEntityBeanType());
		return query.getResultList();

	}

	/**
	 * return the hibernate criteria
	 */
	public org.hibernate.Criteria createHibernateCriteria() {
		org.hibernate.Criteria critetia = getHibernateSession().createCriteria(
				getEntityBeanType());
		return critetia;

	}

	/**
	 * @return hiernate session
	 */
	public org.hibernate.Session getHibernateSession() {
		org.hibernate.Session session = ((org.hibernate.ejb.HibernateEntityManager) getEntityManager())
				.getSession();

		return session;
	}

	/**
	 * {@inheritDoc}
	 */

	public void flushAndClear() {

		flush();
		clear();

	}

	/**
	 * {@inheritDoc}
	 */
	public void flush() {
		getEntityManager().flush();
	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		getEntityManager().clear();
	}

	protected void initializeEntityManager(EntityManager enmgr) {
		this.entityManager = enmgr;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityBeanType(Class<T> entity) {
		this.entityBeanType = entity;
	}

	public Class<T> getEntityBeanType() {
		return this.entityBeanType;
	}

	public Integer delete(String query) {
		Query queryObject = getEntityManager().createQuery(query);
		int deleted = queryObject.executeUpdate();
		return deleted;
	}

	public T executeNamedQueryForSingleResult(String queryName,
			Object... values) {
		List<T> records = findByNamedQuery(queryName, values);
		if (CollectionUtils.isNotEmpty(records)) {
			return records.get(0);
		}
		return null;
	}

	public <F> F executeNamedQueryForSingleResult(Class<F> c, String queryName,
			Object... values) {
		List<F> records = findByNamedQuery(c, queryName, values);
		if (CollectionUtils.isNotEmpty(records)) {
			return records.get(0);
		}
		return null;
	}

	public <F> F executeNamedQueryForSingleResult(Class<F> c, String queryName) {
		List<F> records = findByNamedQuery(c, queryName);
		if (CollectionUtils.isNotEmpty(records)) {
			return records.get(0);
		}
		return null;
	}

	public Integer executeUpdateNamedQuery(String queryName, Object... values) {
		Query queryObject = getEntityManager().createNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i + 1, values[i]);
			}
		}
		return queryObject.executeUpdate();
	}

	public Integer executeUpdateNamedQuery(String queryName) {
		Query queryObject = getEntityManager().createNamedQuery(queryName);
		return queryObject.executeUpdate();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	private List<T> findByJPACriteriaBuilder(
			Map<String, Object> propertyValues, String[] orderBy) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityBeanType);
		Root<T> root = criteria.from(entityBeanType);

		if (!MapUtils.isEmpty(propertyValues)) {
			Predicate condition = builder.conjunction();
			Set<String> keys = propertyValues.keySet();
			for (String key : keys) {
				condition.getExpressions().add(
						builder.equal(root.get(key), propertyValues.get(key)));
			}
			criteria.where(condition);
		}

		if (!ArrayUtils.isEmpty(orderBy)) {
			List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>();
			for (String column : orderBy) {
				orderList.add(builder.asc(root.get(column)));
			}
			criteria.orderBy(orderList);
		}

		TypedQuery<T> t = getEntityManager().createQuery(criteria);

		List<T> result = t.getResultList();

		return result;
	}

/**
* For executing a native query and specifying a return typ when selecting
* data from multiple tables.
* 
* resultType is specified using annotations eg :
* 
* @SqlResultSetMapping(name="TestModelResults", entities =
*                                                                                             {@EntityResult(entityClass=com.sony.ormc.pt.db.dao.model.TestModel.class,
* 
*                                               fields =
* @FieldResult (name="id", column="id"),
* @FieldResult (name="name", column="name")})}) In the above case
*              resultSetType is TestModelResults
* 
* @param queryStr
* @param resultSetType
* @return
*/
	@SuppressWarnings("unchecked")
	public List executeNativeQuery(String queryStr, String resultSetType) {

		Query query = getEntityManager().createNativeQuery(queryStr,
				resultSetType);
		return query.getResultList();

	}
}
