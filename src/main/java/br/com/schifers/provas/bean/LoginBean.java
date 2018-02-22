package br.com.schifers.provas.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.schifers.provas.dto.MenuDTO;
import br.com.schifers.provas.dto.MenuItemDTO;
import br.com.schifers.provas.entity.MenuType;
import br.com.schifers.provas.service.MenuService;

@Stateless
@Named
public class LoginBean {

    @Inject
    private MenuService menuService;

    private MenuDTO menuDto;

    private MenuModel model;

    private String username;

    private String password;

    private Boolean authenticated;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            if (request.getUserPrincipal() == null) {
                request.login(username, password);
                authenticated = true;
                FacesMessage facesMessage = new FacesMessage("Autenticação efetuada com sucesso");
                context.addMessage(null, facesMessage);
                update();
            }
        } catch (ServletException e) {
            FacesMessage facesMessage = new FacesMessage("Usuário ou senha inválidos");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, facesMessage);
            return "/index";
        }
        return "/user/dashboard?faces-redirect=true";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            authenticated = false;
            update();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Falha ao sair"));
        }
        return "/index?faces-redirect=true";
    }

    public void reset() {
        username = "";
        password = "";
    }

    public String criarConta() {
        return "/signup";
    }

    @PostConstruct
    public void update() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

}
