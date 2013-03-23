package se362.composite;

public abstract class HTMLComponent {
	private String name;
	private boolean isClosed;

	public HTMLComponent(String tag) {
		this.name = tag.toLowerCase(); // or to upper, since enums are uppercase
	}

	public String getName() {
		return this.name;
	}

	public boolean isClosed() {
		return this.isClosed;
	}

	public boolean checkValidTag() {
		// check against list of valid tags
		return true;
	}

	// parameter might not be correct
	public boolean add(HTMLComponent node) {
		if (checkValidTag()) {
			// add child to parent
			return true;
		}
		return false;
	}

}