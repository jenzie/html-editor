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
	//public ArrayList<HTMLComponent> children;

	/**
	 * Constructor.
	 * @param tag	html tag that may be opening/closing.
	 * @param parent reference to parent node for checking if closing tag is
	 *               complete.
	 */
	public HTMLComposite(String tag, String closeTag, HTMLComponent parent) {
		super(tag, closeTag, parent);
		//super.children = new ArrayList<HTMLComponent>();
	}

	/**
	 * Getter for tag value.
	 * @return	the html tag value associated.
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * Getter for close tag value.
	 * @return	the html close tag value associated.
	 */
	public String getCloseTag() {
		return super.getCloseTag();
	}
	
	/**
	 * Checks to see if tag was properly closed.
	 * @return	true is tag was closed; false otherwise.
	 */
	public boolean isClosed() {
		return this.isClosed();
	}

	/**
	 * Checks to see if the given tag is a valid html tag.
	 * (Used before adding node of the tag to the tree.)
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
		if (checkValidTag()) {
			((HTMLComposite) this).addChild(child);
			return true;
		} return false;
	}

	/**
	 * Adds the child to list of children.
	 * @param child	html node to add as child.
	 * @return	true if child was added.
	 */
	public boolean addChild(HTMLComponent child) {
		return super.children.add(child);
	}
	
	public String getText() {
		String x = "<" + this.getName() + ">";
		for(HTMLComponent h : children) {
			x += h.getText();
		}
		x += "</" + getCloseTag() + ">\n";
		return x;
	}
}