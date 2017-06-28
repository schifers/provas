package br.com.schifers.concursos.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import br.com.schifers.concursos.entity.Menu;

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
