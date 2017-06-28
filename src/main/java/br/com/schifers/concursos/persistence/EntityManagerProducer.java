package br.com.schifers.concursos.persistence;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "concursosEntityManager")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
