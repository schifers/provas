package br.com.schifers.concursos.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.schifers.concursos.entity.Role;

@Stateless
public class RoleDAO {

    @Inject
    EntityManager manager;

    public Role find(Long id) {
        return manager.find(Role.class, id);
    }

    public Role findByName(String name) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> from = cq.from(Role.class);
        cq.where(cb.equal(from.get("rolename"), name));
        TypedQuery<Role> tq = manager.createQuery(cq);
        return tq.getSingleResult();
    }

}
