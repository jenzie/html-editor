package se362.composite;

import java.util.ArrayList;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
*/

/**
 * HTMLComposite
 * A html node that may have children, and have both an opening and closing tag.
 */
public class HTMLComposite extends HTMLComponent{
	private ArrayList<HTMLComponent> children;

	/**
	 * Constructor.
	 * @param tag	html tag that may be opening/closing.
	 * @param parent reference to parent node for checking if closing tag is
	 *               complete.
	 */
	public HTMLComposite(String tag, HTMLComponent parent) {
		super(tag, parent);
		this.children = new ArrayList<HTMLComponent>();
	}

	/**
	 * Getter for tag value.
	 * @return	the html tag value associated.
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * Checks to see if tag was properly closed.
	 * @return	true is tag was closed; false otherwise.
	 */
	public boolean isClosed() {
		return super.isClosed();
	}

	/**
	 * Checks to see if the given tag is a valid html tag.
	 * (Used before adding node of the tag to the tree.
	 * @return	true if tag is valid html; false otherwise.
	 */
	public boolean checkValidTag(){
		return super.checkValidTag();
	}

	/**
	 * Attempts to add a child to this parent node, if and only if, the child
	 * node is a valid html tag. Otherwise, ignore attempt to add.
	 * @param child	node to add to parent node in tree structure.
	 * @return	true if child was added successfully.
	 */
	public boolean add(HTMLComponent child) {
		return super.add(child);
	}

	/**
	 * Adds the child to list of children.
	 * @param child	html node to add as child.
	 * @return	true if child was added.
	 */
	public boolean addChild(HTMLComponent child) {
		return this.children.add(child);
	}
}