package br.com.schifers.concursos.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.schifers.concursos.dto.MenuDTO;
import br.com.schifers.concursos.dto.MenuItemDTO;
import br.com.schifers.concursos.entity.MenuType;
import br.com.schifers.concursos.service.MenuService;

@Stateless
@Named
public class MenuBean {

    @Inject
    private MenuService menuService;

    private MenuDTO menuDto;

    private MenuModel model;

    @PostConstruct
    public void init() {
        menuDto = menuService.buildMenu("principal");

        model = new DefaultMenuModel();

        for (MenuItemDTO root : menuDto.getRoots()) {
            if (root.getType().equals(MenuType.ITEM)) {
                DefaultMenuItem item = montarItem(root);
                model.addElement(item);
            }
            if (root.getType().equals(MenuType.LIST)) {
                DefaultSubMenu submenu = new DefaultSubMenu(root.getName());
                buildSubmenu(root, submenu);
                model.addElement(submenu);
            }
        }
    }

    private DefaultMenuItem montarItem(MenuItemDTO dto) {
        DefaultMenuItem item = new DefaultMenuItem(dto.getName());
        if (dto.getAction() != null) {
            item.setCommand(dto.getAction());
            item.setUpdate(dto.getUpdate());
        }
        if (dto.getUrl() != null) {
            item.setUrl(dto.getUrl());
        }
        if (dto.getOutcome() != null) {
            item.setOutcome(dto.getOutcome());
        }
        return item;
    }

    public void buildSubmenu(MenuItemDTO root, DefaultSubMenu submenu) {
        for (MenuItemDTO child : menuDto.getMenuItemsMap().get(root.getId())) {
            if (child.getType().equals(MenuType.ITEM)) {
                DefaultMenuItem item = montarItem(child);
                submenu.addElement(item);
            }
            if (child.getType().equals(MenuType.LIST)) {
                DefaultSubMenu subchildmenu = new DefaultSubMenu(child.getName());
                buildSubmenu(child, subchildmenu);
                submenu.addElement(subchildmenu);
            }
        }
    }

    public MenuDTO getMenuDto() {
        return menuDto;
    }

    public void setMenuDto(MenuDTO menuDto) {
        this.menuDto = menuDto;
    }

    public MenuModel getModel() {
        return model;
    }

}
