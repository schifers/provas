package br.com.schifers.provas.bean;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.schifers.provas.dao.MenuDAO;
import br.com.schifers.provas.dao.MenuItemDAO;
import br.com.schifers.provas.dao.PersonDAO;
import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.entity.Person;
import br.com.schifers.provas.service.MenuService;

@Stateless
@Named
public class MenuBean {

	@Inject
	private MenuItemDAO menuItemDao;

	@Inject
	private MenuDAO menuDao;

	@Inject
	private MenuService menuService;

	@Inject
	private PersonDAO personDao;

	private Integer id;

	private Menu menu;

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

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			menu = menuDao.findById(id);
		} else {
			menu = new Menu();
		}
	}

	public void save() {
		String msg;
		if (menu.getId() == null) {
			menuService.insert(menu);
			msg = "Menu " + menu.getName() + " foi criado com sucesso";
		} else {
			menuService.update(menu);
			msg = "Menu " + menu.getName() + " foi alterado com sucesso";
		}
		addDetailMessage(msg, null);
	}

	public void clear() {
		menu = new Menu();
		id = null;
	}

	public void remove() throws IOException {
		if (has(menu) && has(menu.getId())) {
			menuService.delete(menu);
			addDetailMessage("Menu " + menu.getName() + " foi apagado com sucesso", null);
			Faces.getFlash().setKeepMessages(true);
			Faces.redirect("menu-list.xhtml");
		}
	}

	public boolean isNew() {
		return menu == null || menu.getId() == null;
	}

	public static void addDetailMessage(String message, FacesMessage.Severity severity) {
		FacesMessage facesMessage = Messages.create("").detail(message).get();
		if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
			facesMessage.setSeverity(severity);
		}
		Messages.add(null, facesMessage);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
