package br.com.schifers.provas.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

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

	public List<Menu> findAllByName(String name) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> from = query.from(Menu.class);
		query.where(cb.like(from.get("name"), "%" + name));
		TypedQuery<Menu> tq = manager.createQuery(query);
		return tq.getResultList();
	}

	public Menu findById(Integer id) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> from = query.from(Menu.class);
		query.where(cb.equal(from.get("id"), id));
		TypedQuery<Menu> tq = manager.createQuery(query);
		return tq.getSingleResult();
	}

	public List<Menu> findAll(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> from = query.from(Menu.class);
		Path<String> pathString = from.get("name");
		Path<?> path = from.get("name");
		Predicate filterCondition = cb.conjunction();
		if (pathString != null) {
			if (sortOrder == null || sortOrder.equals(SortOrder.ASCENDING)) {
				query.orderBy(cb.asc(pathString));
			} else if (sortOrder.equals(SortOrder.DESCENDING)) {
				query.orderBy(cb.desc(pathString));
			}

		} else {
			if (sortOrder == null || sortOrder.equals(SortOrder.ASCENDING)) {
				query.orderBy(cb.asc(path));
			} else if (sortOrder.equals(SortOrder.DESCENDING)) {
				query.orderBy(cb.desc(path));
			}
		}
		for (Map.Entry<String, String> filter : filters.entrySet()) {
			if (pathString != null) {
				filterCondition = cb.and(filterCondition, cb.like(pathString, "%" + filter.getValue() + "%"));
			} else if (path != null) {
				filterCondition = cb.and(filterCondition, cb.equal(path, filter.getValue()));
			}
		}
		query.where(filterCondition);
		TypedQuery<Menu> tq = manager.createQuery(query);
		if (pageSize >= 0) {
			tq.setMaxResults(pageSize);
		}
		if (first >= 0) {
			tq.setFirstResult(first);
		}
		return tq.getResultList();
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
