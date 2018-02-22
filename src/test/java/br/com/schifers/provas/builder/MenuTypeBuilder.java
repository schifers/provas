package br.com.schifers.provas.builder;

import br.com.schifers.provas.entity.MenuType;

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
