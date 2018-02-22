package br.com.schifers.provas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", schema = "provas")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rolename;

    @OneToMany(mappedBy = "role")
    private List<RoleMenuItem> roleMenuItems;

    @OneToMany(mappedBy = "role")
    private List<PersonRole> personRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<RoleMenuItem> getRoleMenuItems() {
        return roleMenuItems;
    }

    public void setRoleMenuItems(List<RoleMenuItem> roleMenuItems) {
        this.roleMenuItems = roleMenuItems;
    }

    public List<PersonRole> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(List<PersonRole> personRoles) {
        this.personRoles = personRoles;
    }

}
