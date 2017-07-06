package br.com.schifers.concursos.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import br.com.schifers.concursos.entity.MenuItem;
import br.com.schifers.concursos.entity.MenuMenuItem;
import br.com.schifers.concursos.entity.PersonRole;
import br.com.schifers.concursos.entity.Role;
import br.com.schifers.concursos.entity.RoleMenuItem;

@Stateless
public class MenuItemDAO {

    @Inject
    private EntityManager manager;

    public List<MenuItem> findByMenu(Long id) {
        Metamodel m = manager.getMetamodel();
        EntityType<MenuItem> MenuItem_ = m.entity(MenuItem.class);

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MenuItem> cq = cb.createQuery(MenuItem.class);
        Root<MenuItem> from = cq.from(MenuItem.class);
        ListJoin<MenuItem, MenuMenuItem> join = from.join(MenuItem_.getList("menuMenuItems", MenuMenuItem.class));
        Predicate p = cb.equal(join.get("menu").get("id"), id);
        cq.where(p);
        TypedQuery<MenuItem> tq = manager.createQuery(cq);
        return tq.getResultList();
    }

    public List<MenuItem> findByMenuByPerson(Long idMenu, Long idPerson) {
        Metamodel m = manager.getMetamodel();
        EntityType<MenuItem> MenuItem_ = m.entity(MenuItem.class);
        EntityType<Role> Role_ = m.entity(Role.class);

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MenuItem> cq = cb.createQuery(MenuItem.class);
        Root<MenuItem> from = cq.from(MenuItem.class);
        ListJoin<MenuItem, MenuMenuItem> joinMenuMenuItem = from.join(MenuItem_.getList("menuMenuItems", MenuMenuItem.class));
        ListJoin<MenuItem, RoleMenuItem> joinRoleMenuItem = from.join(MenuItem_.getList("roleMenuItems", RoleMenuItem.class));
        Join<RoleMenuItem, Role> joinRole = joinRoleMenuItem.join("role");
        ListJoin<Role, PersonRole> joinPersonRole = joinRole.join(Role_.getList("personRoles", PersonRole.class));
        Predicate p1 = cb.equal(joinMenuMenuItem.get("menu").get("id"), idMenu);
        Predicate p2 = cb.equal(joinPersonRole.get("person").get("id"), idPerson);
        cq.where(p1, p2);
        cq.groupBy(from);
        TypedQuery<MenuItem> tq = manager.createQuery(cq);
        return tq.getResultList();
    }

}
