package br.com.schifers.concursos.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schifers.concursos.dto.MenuDTO;
import br.com.schifers.concursos.service.MenuService;

@Stateless
@Named
public class MenuBean {

    @Inject
    private MenuService menuService;

    private MenuDTO menuDto;

    public MenuBean() {
        menuDto = menuService.buildMenu("principal");
    }

    public MenuDTO getMenuDto() {
        return menuDto;
    }

    public void setMenuDto(MenuDTO menuDto) {
        this.menuDto = menuDto;
    }

}
