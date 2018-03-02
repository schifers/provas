package br.com.schifers.provas.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import br.com.schifers.provas.entity.Menu;

public class MenuDTO {

	private Menu menu;

	private List<MenuItemDTO> roots;

	private HashMap<Long, List<MenuItemDTO>> menuItemsMap;

	public MenuDTO() {
		roots = new ArrayList<MenuItemDTO>();
		menuItemsMap = new HashMap<Long, List<MenuItemDTO>>();
	}

	public void orderRoots() {
		Collections.sort(roots, new Comparator<MenuItemDTO>() {
			@Override
			public int compare(MenuItemDTO o1, MenuItemDTO o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
	}

	public void orderMenuItems(List<MenuItemDTO> unorderedList) {
		Collections.sort(unorderedList, new Comparator<MenuItemDTO>() {
			@Override
			public int compare(MenuItemDTO o1, MenuItemDTO o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
	}

	/**
	 * Build a list of menu items with level information
	 * 
	 * @return
	 */
	public List<MenuItemDTO> buildMenusWithLevel() {
		List<MenuItemDTO> menusWithLevel = new ArrayList<MenuItemDTO>();
		for (MenuItemDTO root : getRoots()) {
			Integer level = 1;
			root.setLevel(0);
			menusWithLevel.add(root);
			orderMenuItems(this.getMenuItemsMap().get(root.getId()));
			buildTree(this.getMenuItemsMap().get(root.getId()), level, menusWithLevel);
		}
		return menusWithLevel;
	}

	private Integer buildTree(List<MenuItemDTO> children, Integer level, List<MenuItemDTO> menusWithLevel) {
		for (MenuItemDTO child : children) {
			child.setLevel(level);
			menusWithLevel.add(child);
			this.getMenuItemsMap().get(child.getId());
			if (this.getMenuItemsMap().get(child.getId()).size() > 0) {
				level = buildTree(this.getMenuItemsMap().get(child.getId()), ++level, menusWithLevel);
			}
		}
		if (children.size() > 0) {
			level--;
		}
		return level;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<MenuItemDTO> getRoots() {
		return roots;
	}

	public void setRoots(List<MenuItemDTO> roots) {
		this.roots = roots;
	}

	public HashMap<Long, List<MenuItemDTO>> getMenuItemsMap() {
		return menuItemsMap;
	}

	public void setMenuItemsMap(HashMap<Long, List<MenuItemDTO>> menuItemsMap) {
		this.menuItemsMap = menuItemsMap;
	}

}
