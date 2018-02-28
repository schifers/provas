package br.com.schifers.provas.bean;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.schifers.provas.dao.MenuDAO;
import br.com.schifers.provas.dao.MenuItemDAO;
import br.com.schifers.provas.dao.PersonDAO;
import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.entity.Person;

@Stateless
@Named
public class MenuBean {

	@Inject
	private MenuItemDAO menuItemDao;

	@Inject
	private MenuDAO menuDao;

	@Inject
	private PersonDAO personDao;

	private Principal getPrincipal() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request.getUserPrincipal();
	}

	public List<MenuItem> getMenuPrincipal() {
		Menu menu = menuDao.findByName("principal");

		Principal principal = getPrincipal();

		Person person = personDao.findByUsername(principal == null ? Person.GUEST : principal.getName());

		return menuItemDao.findByMenuByPerson(menu.getId(), person.getId());
	}

}
