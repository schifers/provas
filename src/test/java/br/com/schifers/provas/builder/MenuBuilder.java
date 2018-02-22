package br.com.schifers.provas.builder;

import br.com.schifers.provas.entity.Menu;

public class MenuBuilder {

    private Menu entidade;

    public MenuBuilder() {
        this.entidade = new Menu();
    }

    public MenuBuilder id(Long id) {
        this.entidade.setId(id);
        return this;
    }

    public MenuBuilder name(String name) {
        this.entidade.setName(name);
        return this;
    }

    public Menu build() {
        return this.entidade;
    }

}
