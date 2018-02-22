package br.com.schifers.provas.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.schifers.provas.entity.Person;

@Stateless
public class PersonDAO {

    @Inject
    private EntityManager manager;

    public Person insert(Person person) {
        manager.persist(person);
        return person;
    }

    public Person findByUsername(String username) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> from = cq.from(Person.class);
        cq.where(cb.equal(from.get("username"), username));
        TypedQuery<Person> tq = manager.createQuery(cq);
        return tq.getSingleResult();
    }

}
