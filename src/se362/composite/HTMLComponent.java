package se362.composite;

import se362.HTMLConstructs;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
 */

public abstract class HTMLComponent {
	private String name;
	private boolean isClosed;

	/**
	 * Constructor.
	 * @param tag	html tag that may be opening/closing.
	 */
	public HTMLComponent(String tag) {
		this.name = tag.toLowerCase(); // or to upper, since enums are uppercase
	}

	/**
	 * Getter for tag value.
	 * @return	the html tag value associated.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Checks to see if tag was properly closed.
	 * @return	true is tag was closed; false otherwise.
	 */
	public boolean isClosed() {
		return this.isClosed;
	}

	/**
	 * Checks to see if the given tag is a valid html tag.
	 * (Used before adding node of the tag to the tree.
	 * @return	true if tag is valid html; false otherwise.
	 */
	public boolean checkValidTag() {
		// check against list of valid tags; ignore upper/lowercase
		for (HTMLConstructs tag : HTMLConstructs.values()) {
			if (tag.name().equalsIgnoreCase(this.name))
				return true;
		} return false;
	}

	/**
	 * Attempts to add a child to this parent node, if and only if, the child
	 * node is a valid html tag. Otherwise, ignore attempt to add.
	 * @param child	node to add to parent node in tree structure.
	 * @return	true if child was added successfully.
	 */
	public boolean add(HTMLComponent child) {
		if (checkValidTag()) {
			((HTMLComposite) this).addChild(child);
			return true;
		} return false;
	}
}