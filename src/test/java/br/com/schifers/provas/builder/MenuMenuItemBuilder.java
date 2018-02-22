package br.com.schifers.provas.builder;

import br.com.schifers.provas.entity.Menu;
import br.com.schifers.provas.entity.MenuItem;
import br.com.schifers.provas.entity.MenuMenuItem;

public class MenuMenuItemBuilder {

    private MenuMenuItem entidade;

    public MenuMenuItemBuilder() {
        this.entidade = new MenuMenuItem();
    }

    public MenuMenuItemBuilder id(Long id) {
        this.entidade.setId(id);
        return this;
    }

    public MenuMenuItemBuilder order(Integer order) {
        this.entidade.setOrder(order);
        return this;
    }

    public MenuMenuItemBuilder menu(Menu menu) {
        this.entidade.setMenu(menu);
        return this;
    }

    public MenuMenuItemBuilder menuItem(MenuItem menuItem) {
        this.entidade.setMenuItem(menuItem);
        return this;
    }

    public MenuMenuItem build() {
        return this.entidade;
    }

}
