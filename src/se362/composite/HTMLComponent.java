package se362.composite;

public abstract class HTMLComponent {
	private String name;

	public HTMLComponent(String tag) {
		this.name = tag.toLowerCase();
	}

	public String getName() {
		return this.name;
	}




}