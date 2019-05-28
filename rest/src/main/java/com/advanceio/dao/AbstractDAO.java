package com.advanceio.dao;

import com.advanceio.entity.Entity;

//@Transactional
//@Repository
public abstract class AbstractDAO<E extends Entity<K>, K> {
/*
    public static final int MAX_RESULTS_RETURNED = 5000;

    @PersistenceContext
    protected EntityManager em;

    public E create(E entity) {
        em.persist(entity);
        return entity;
    }

    public E update(E entity) {
        return em.merge(entity);
    }

    public E find(Long id) {

        E entity = em.find(getEntityType(), id);
        if (entity == null) {
            String error = String.format("Entity [%s] not found with id %s", getEntityType().getSimpleName(), id);
            throw new NotFoundException(error);
        }

        return entity;
    }

    public E delete(E entity) {
        em.remove(entity);
        return entity;
    }

    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityType());
        Root<E> root = criteriaQuery.from(getEntityType());

        criteriaQuery.select(root);
        TypedQuery<E> allQuery = em.createQuery(criteriaQuery);
        allQuery.setMaxResults(MAX_RESULTS_RETURNED);

        return allQuery.getResultList();
    }

    protected abstract Class<E> getEntityType();*/
}
