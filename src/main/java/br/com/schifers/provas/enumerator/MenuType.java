package br.com.schifers.provas.enumerator;

public enum MenuType {

	IT("IT", "ITEM"), //
	LS("LS", "LIST"); //

	private String id;

	private String name;

	private MenuType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
