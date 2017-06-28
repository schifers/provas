package br.com.schifers.concursos.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.schifers.concursos.entity.Person;

@Stateless
public class PersonDAO {

    @Inject
    private EntityManager manager;

    public Person insert(Person person) {
        manager.persist(person);
        return person;
    }

}
