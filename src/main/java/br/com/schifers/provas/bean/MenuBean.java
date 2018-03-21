package br.com.schifers.provas.bean;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.github.adminfaces.template.exception.BusinessException;

import br.com.schifers.provas.dao.MenuDAO;
import br.com.schifers.provas.dto.MenuDTO;
import br.com.schifers.provas.dto.MenuItemDTO;
import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.service.MenuService;

@Stateless
@Named
public class MenuBean implements Serializable {

	private static final long serialVersionUID = -4405362522644315109L;

	@Inject
	private MenuDAO menuDao;

	@Inject
	private MenuService menuService;

	private Integer id;

	LazyDataModel<Menu> menus;

	private Menu menu;

	List<Menu> selectedMenus;

	List<Menu> filteredValue;

	private Menu filter;

	@PostConstruct
	public void initDataModel() {
		menus = new LazyDataModel<Menu>() {
			private static final long serialVersionUID = -3224595409792139354L;

			@Override
			public List<Menu> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				Map<String, String> filterConverted = new HashMap<String, String>();
				for (String key : filters.keySet()) {
					filterConverted.put(key, (String) filters.get(key));
				}
				List<Menu> result = menuService.getResultList(first, pageSize, sortField, sortOrder, filterConverted);
				menus.setRowCount(menuService.count(sortField, sortOrder, filterConverted));
				return result;
			}
		};
	}

	public List<MenuItemDTO> getMenuPrincipal() {
		MenuDTO dto = menuService.buildMenu("principal");
		return dto.buildMenusWithLevel();
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

	public void delete() {
		int numMenus = 0;
		for (Menu selectedMenu : selectedMenus) {
			numMenus++;
			menuService.delete(selectedMenu);
		}
		selectedMenus.clear();
		addDetailMessage(numMenus + " menus apagados com sucesso!", null);
	}
	
	public void query() {
		
	}

	public void findMenuById(Integer id) {
		if (id == null) {
			throw new BusinessException("Informe o ID para realizar a consulta");
		}
		selectedMenus.add(menuService.findById(id));
	}

	public void findMenuByName(String name) {
		if (name == null) {
			throw new BusinessException("Nome é obrigatório para realizar a consulta");
		}
		filteredValue.addAll(menuService.findAllByName(name));
	}

	public List<String> completeModel(String query) {
		List<String> result = menuService.getNames(query);
		return result;
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

	public List<Menu> getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(List<Menu> selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	public List<Menu> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Menu> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public LazyDataModel<Menu> getMenus() {
		return menus;
	}

	public void setMenus(LazyDataModel<Menu> menus) {
		this.menus = menus;
	}

	public Menu getFilter() {
		return filter;
	}

	public void setFilter(Menu filter) {
		this.filter = filter;
	}

}
