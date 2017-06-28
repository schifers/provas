package br.com.schifers.concursos.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.schifers.concursos.entity.MenuItem;

@Stateless
public class MenuItemDAO {

    @Inject
    private EntityManager manager;

    public List<MenuItem> findByMenu(Long id) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MenuItem> criteriaQuery = cb.createQuery(MenuItem.class);
        Root<MenuItem> from = criteriaQuery.from(MenuItem.class);
        Predicate p = cb.equal(from.get("menu").get("id"), id);
        criteriaQuery.where(p);
        TypedQuery<MenuItem> tq = manager.createQuery(criteriaQuery);
        return tq.getResultList();
    }

}
