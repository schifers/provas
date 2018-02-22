package br.com.schifers.provas.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.schifers.provas.entity.PersonRole;

@Stateless
public class PersonRoleDAO {

    @Inject
    private EntityManager manager;

    public PersonRole insert(PersonRole personRole) {
        manager.persist(personRole);
        return personRole;
    }

}
