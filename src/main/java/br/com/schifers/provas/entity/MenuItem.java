package br.com.schifers.provas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.schifers.provas.enumerator.MenuType;

@Entity
@Table(name = "menu_item", schema = "provas")
public class MenuItem {

	@Id
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String action;

	@Column
	private String url;

	@Column
	private String icon;

	@Column(name = "item_type")
	@Enumerated(EnumType.STRING)
	private MenuType menuType;

	@ManyToOne
	@JoinColumn(name = "menu_item_id", referencedColumnName = "id")
	private MenuItem parent;

	@OneToMany(mappedBy = "menuItem")
	private List<MenuMenuItem> menuMenuItems;

	@OneToMany(mappedBy = "menuItem")
	private List<RoleMenuItem> roleMenuItems;

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

	public List<MenuMenuItem> getMenuMenuItems() {
		return menuMenuItems;
	}

	public void setMenuMenuItems(List<MenuMenuItem> menuMenuItems) {
		this.menuMenuItems = menuMenuItems;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<RoleMenuItem> getRoleMenuItems() {
		return roleMenuItems;
	}

	public void setRoleMenuItems(List<RoleMenuItem> roleMenuItems) {
		this.roleMenuItems = roleMenuItems;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
