package br.com.schifers.provas.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.schifers.provas.dao.MenuDAO;
import br.com.schifers.provas.dao.MenuItemDAO;
import br.com.schifers.provas.dao.MenuMenuItemDAO;
import br.com.schifers.provas.dao.PersonDAO;
import br.com.schifers.provas.dto.MenuDTO;
import br.com.schifers.provas.dto.MenuItemDTO;
import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.entity.MenuMenuItem;
import br.com.schifers.provas.entity.Person;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MenuService {

	@Inject
	private MenuDAO menuDao;

	@Inject
	private MenuItemDAO menuItemDao;

	@Inject
	private MenuMenuItemDAO menuMenuItemDao;

	@Inject
	private PersonDAO personDao;

	private Principal getPrincipal() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request.getUserPrincipal();
	}

	public MenuDTO buildMenu(String name) {
		Principal principal = getPrincipal();

		Menu menu = menuDao.findByName(name);

		MenuDTO dto = new MenuDTO();

		dto.setMenu(menu);

		Person person = personDao.findByUsername(principal == null ? Person.GUEST : principal.getName());

		List<MenuItem> menuItems = menuItemDao.findByMenuByPerson(menu.getId(), person.getId());

		for (MenuItem menuItem : menuItems) {
			MenuMenuItem menuMenuItem = menuMenuItemDao.findByMenuByMenuItem(menu.getId(), menuItem.getId());
			MenuItemDTO menuItemDTO = new MenuItemDTO();
			menuItemDTO.setId(menuItem.getId());
			menuItemDTO.setName(menuItem.getName());
			menuItemDTO.setAction(menuItem.getAction());
			menuItemDTO.setUrl(menuItem.getUrl());
			menuItemDTO.setParentId(menuItem.getParent() == null ? null : menuItem.getParent().getId());
			menuItemDTO.setType(menuItem.getMenuType().getName());
			menuItemDTO.setOrder(menuMenuItem.getOrdering());
			if (menuItem.getParent() == null) {
				dto.getRoots().add(menuItemDTO);
			} else {
				if (dto.getMenuItemsMap().containsKey(menuItem.getParent().getId())) {
					dto.getMenuItemsMap().get(menuItem.getParent().getId()).add(menuItemDTO);
				} else {
					dto.getMenuItemsMap().put(menuItem.getParent().getId(), new ArrayList<MenuItemDTO>());
				}
			}
			if (!dto.getMenuItemsMap().containsKey(menuItem.getId())) {
				dto.getMenuItemsMap().put(menuItem.getId(), new ArrayList<MenuItemDTO>());
			}
		}

		dto.orderRoots();

		return dto;
	}

}
