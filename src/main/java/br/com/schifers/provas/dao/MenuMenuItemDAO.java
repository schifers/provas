package br.com.schifers.provas.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.schifers.provas.entity.MenuMenuItem;

@Stateless
public class MenuMenuItemDAO {

    @Inject
    private EntityManager manager;

    public MenuMenuItem findByMenuByMenuItem(Long idMenu, Long idMenuItem) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MenuMenuItem> cq = cb.createQuery(MenuMenuItem.class);
        Root<MenuMenuItem> from = cq.from(MenuMenuItem.class);
        Predicate p1 = cb.equal(from.get("menu").get("id"), idMenu);
        Predicate p2 = cb.equal(from.get("menuItem").get("id"), idMenuItem);
        cq.where(p1, p2);
        TypedQuery<MenuMenuItem> tq = manager.createQuery(cq);
        return tq.getSingleResult();
    }

}
