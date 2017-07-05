package br.com.schifers.concursos.builder;

import br.com.schifers.concursos.entity.Menu;
import br.com.schifers.concursos.entity.MenuItem;
import br.com.schifers.concursos.entity.MenuMenuItem;

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
