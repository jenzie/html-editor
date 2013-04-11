package se362.composite;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
 * @author Chad Koppes, chadkoppes@gmail.com
 */

/**
 * HTMLLeaf
 * A html node that cannot have children, and does not need a closing tag.
 */
public class HTMLLeaf extends HTMLComponent{

	/**
	 * Constructor.
	 * @param tag	html tag that may be opening/closing.
	 * @param parent  reference to parent node for checking for complete
	 *                tag structure.
	 */
	public HTMLLeaf(String tag, String closeTag, HTMLComponent parent) {
		super(tag, "", parent);
		super.isClosed = true;
	}

	/**
	 * Getter for tag value.
	 * @return	the html tag value associated.
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * Checks to see if leaf node was closed by a closing tag.
	 * @return	false, no matter what.
	 */
	public boolean isClosed() {
		return false;
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
	 * Unable to add children to lead nodes.
	 * @param node	child node to add.
	 * @return	false, no matter what.
	 */
	public boolean add(HTMLComponent node) {
		return false;
	}
}
