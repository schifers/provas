package br.com.schifers.concursos.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.schifers.concursos.entity.PersonRole;

@Stateless
public class PersonRoleDAO {

    @Inject
    private EntityManager manager;

    public PersonRole insert(PersonRole personRole) {
        manager.persist(personRole);
        return personRole;
    }

}
