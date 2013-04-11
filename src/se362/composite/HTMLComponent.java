package se362.composite;

import java.util.ArrayList;

import se362.HTMLConstructs;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
 * @author Chad Koppes, chadkoppes@gmail.com
 */

/**
 * HTMLComponent
 * Defines methods that html nodes may use or override if extending from this
 * class (particularly, HTMLComposite and HTMLLeaf).
 */
public abstract class HTMLComponent {
	private String name;
	private String closeTag;
	public boolean isClosed;
	public HTMLComponent parent;
	public ArrayList<HTMLComponent> children = new ArrayList<HTMLComponent>();

	/**
	 * Constructor.
	 * @param tag	html tag that may be opening/closing.
	 */
	public HTMLComponent(String tag, String closeTag, HTMLComponent parent) {
		this.name = tag; // or to upper, since enums are uppercase
		this.closeTag = closeTag;
		this.parent = parent;
		this.isClosed = false;
	}

	/**
	 * Getter for tag value.
	 * @return	the html tag value associated.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for close tag value.
	 * @return	the html close tag value associated.
	 */
	public String getCloseTag() {
		return this.closeTag;
	}
	
	/**
	 * Setter for close tag value.
	 * @return	the html close tag value associated.
	 */
	public String setCloseTag(String closeTag) {
		return this.closeTag;
	}
	
	/**
	 * Checks to see if tag was properly closed.
	 * @return	true is tag was closed; false otherwise.
	 */
	public abstract boolean isClosed();

	/**
	 * Checks to see if the given tag is a valid html tag.
	 * (Used before adding node of the tag to the tree.)
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
	public abstract boolean add(HTMLComponent child);
}