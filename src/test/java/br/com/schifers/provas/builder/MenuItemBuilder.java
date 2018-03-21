package br.com.schifers.provas.builder;

import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.enumerator.MenuType;

public class MenuItemBuilder {

	private MenuItem entidade;

	public MenuItemBuilder() {
		this.entidade = new MenuItem();
	}

	public MenuItemBuilder id(Long id) {
		this.entidade.setId(id);
		return this;
	}

	public MenuItemBuilder name(String name) {
		this.entidade.setName(name);
		return this;
	}

	public MenuItemBuilder action(String action) {
		this.entidade.setAction(action);
		return this;
	}

	public MenuItemBuilder menuType(MenuType menuType) {
		this.entidade.setMenuType(menuType);
		return this;
	}

	public MenuItemBuilder parent(MenuItem parent) {
		this.entidade.setParent(parent);
		return this;
	}

	public MenuItem build() {
		return this.entidade;
	}

}
