package br.com.schifers.concursos.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.schifers.concursos.dao.MenuDAO;
import br.com.schifers.concursos.dao.MenuItemDAO;
import br.com.schifers.concursos.dao.MenuMenuItemDAO;
import br.com.schifers.concursos.dto.MenuDTO;
import br.com.schifers.concursos.dto.MenuItemDTO;
import br.com.schifers.concursos.entity.Menu;
import br.com.schifers.concursos.entity.MenuItem;
import br.com.schifers.concursos.entity.MenuMenuItem;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MenuService {

    @Inject
    private MenuDAO menuDao;

    @Inject
    private MenuItemDAO menuItemDao;

    @Inject
    private MenuMenuItemDAO menuMenuItemDao;

    public MenuDTO buildMenu(String name) {
        Menu menu = menuDao.findByName(name);

        MenuDTO dto = new MenuDTO();

        dto.setMenu(menu);

        List<MenuItem> menuItems = menuItemDao.findByMenu(menu.getId());

        for (MenuItem menuItem : menuItems) {
            MenuMenuItem menuMenuItem = menuMenuItemDao.findByMenuByMenuItem(menu.getId(), menuItem.getId());
            MenuItemDTO menuItemDTO = new MenuItemDTO();
            menuItemDTO.setId(menuItem.getId());
            menuItemDTO.setName(menuItem.getName());
            menuItemDTO.setAction(menuItem.getAction());
            menuItemDTO.setUrl(menuItem.getUrl());
            menuItemDTO.setParentId(menuItem.getParent() == null ? null : menuItem.getParent().getId());
            menuItemDTO.setType(menuItem.getMenuType().getName());
            menuItemDTO.setOrder(menuMenuItem.getOrder());
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
