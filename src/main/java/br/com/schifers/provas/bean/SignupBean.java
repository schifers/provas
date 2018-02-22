package br.com.schifers.provas.bean;

import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schifers.provas.dto.SignupDTO;
import br.com.schifers.provas.service.PersonService;

@Stateless
@Named
public class SignupBean {

    @Inject
    private PersonService personService;

    private String username;

    private String password;

    private String passwordConfirm;

    public String createAccount() {
        FacesContext context = FacesContext.getCurrentInstance();
        SignupDTO dto = new SignupDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setPasswordConfirm(passwordConfirm);
        try {
            personService.insert(dto);
        } catch (NoSuchAlgorithmException e) {
            FacesMessage fm = new FacesMessage("Erro ao criptografar a senha");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, fm);
            return "/signup";
        }
        context.addMessage(null, new FacesMessage("Conta criada com sucesso"));
        return "/user/dashboard";
    }

    public void reset() {
        username = "";
        password = "";
        passwordConfirm = "";
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
