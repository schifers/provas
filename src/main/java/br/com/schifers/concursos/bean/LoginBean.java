package br.com.schifers.concursos.bean;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
@Named
public class LoginBean {

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

}
