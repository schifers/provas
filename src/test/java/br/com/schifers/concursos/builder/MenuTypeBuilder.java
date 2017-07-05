package br.com.schifers.concursos.builder;

import br.com.schifers.concursos.entity.MenuType;

public class MenuTypeBuilder {

    private MenuType entidade;

    public MenuTypeBuilder() {
        this.entidade = new MenuType();
    }

    public MenuTypeBuilder id(Long id) {
        this.entidade.setId(id);
        return this;
    }

    public MenuTypeBuilder name(String name) {
        this.entidade.setName(name);
        return this;
    }

    public MenuType build() {
        return this.entidade;
    }

}
