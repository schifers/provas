package br.com.schifers.provas.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.schifers.provas.entity.Menu;

@Stateless
public class MenuDAO {

	@Inject
	private EntityManager manager;

	public Menu findByName(String name) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> from = query.from(Menu.class);
		query.where(cb.equal(from.get("name"), name));
		TypedQuery<Menu> tq = manager.createQuery(query);
		return tq.getSingleResult();
	}

	public Menu findById(Integer id) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> from = query.from(Menu.class);
		query.where(cb.equal(from.get("id"), id));
		TypedQuery<Menu> tq = manager.createQuery(query);
		return tq.getSingleResult();
	}

	public Menu insert(Menu menu) {
		// TODO: implement save
		return null;
	}

	public Menu update(Menu menu) {
		// TODO: implement update
		return null;
	}

	public void delete(Menu menu) {
		// TODO: implement delete
	}

}
