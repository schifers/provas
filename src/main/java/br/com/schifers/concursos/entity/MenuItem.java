package br.com.schifers.concursos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu_item", schema = "concursos")
public class MenuItem {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String action;

    @ManyToOne
    @JoinColumn(name = "menu_type_id", referencedColumnName = "id")
    private MenuType menuType;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private MenuItem parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

}
