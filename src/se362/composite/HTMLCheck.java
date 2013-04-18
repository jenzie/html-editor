package se362.composite;

import net.htmlparser.jericho.*;
import se362.HTMLConstructs;
import se362.gui.GUI;

/**
 * HTMLCheck. For parsing file for HTML tags and building composite tree.
 * 
 * @author Chad Koppes, chadkoppes@gmail.com
 * @author Jenny Zhen, coffee@csh.rit.edu
 */

public class HTMLCheck {

    private GUI editor;
    private HTMLComponent current = null;
    private int count = 0;

    public HTMLCheck(GUI editor) {
        this.editor = editor;
    }

    /**
     * doCheck() Builds an ArrayList of all tags contained in the file document.
     * Iterates through the list to check A) if it is a valid tag B) if it is an
     * opening tag or closing tag. Calls helper methods to check valid and to
     * build the tree based on whether it is opening or closing tag.
     * 
     * @return HTMLComponent root node of tree if successfully built, null if
     *         not.
     */

    public HTMLComponent doCheck() {
        String file = "";
        // System.out.println("---- STARTING CHECK ----");
        count = 0;
        file = editor.getActiveFileWindow().getAllText();
        Source source = new Source(file);

        for (Segment segment : source) {
            if (segment instanceof Tag) {
                Tag tag = (Tag) segment;
                TagType x = tag.getTagType();
                if (tag.toString().contains("<img")) {
                    addLeafNode(tag.toString());
                    continue;
                }
                if (x.toString().equals("normal")) {
                    addHTMLNode(tag.getName());
                } else {
                    if (checkCloseTag(tag.getName()) == false) {
                        editor.setMessage("HTML is not well-formed.");
                        return null;
                    }
                }
            } else {
                if (!segment.isWhiteSpace()) {
                    addLeafNode(segment.toString());
                }
            }
        }
        if (validTree()) {
            // System.out.println(current.getText());
            editor.getActiveFileWindow().setRootNode(current);
            return current;
        } else {
            editor.getActiveFileWindow().setRootNode(null);
            return null;
        }
    }

    /**
     * Checks to see if the current tree is a valid tree by checking to see if
     * the current node is the root node. Sets the editor message appropriately.
     * 
     * @return true if tree is valid, false if tree is invalid
     */
    private boolean validTree() {
        if ((current == null) || (current.parent != null)) {
            editor.setMessage("HTML is not well-formed.");
            return false;
        }
        return true;
    }

    /**
     * Checks to see if the given tag is a valid html tag. (Used before adding
     * node of the tag to the tree. For testing purposes, it is public and
     * static.
     * 
     * @return true if tag is valid html; false otherwise.
     */
    public static boolean checkValid(String tag) {
        String open, arg;
        // System.out.println("checkValid() initiated");

        // check against list of valid tags; ignore upper/lowercase
        for (HTMLConstructs enumTag : HTMLConstructs.values()) {
            if (enumTag.getArgument() != null) {
                open = tag.substring(0, enumTag.getOpenTag().length());
                if (open.equalsIgnoreCase(enumTag.getOpenTag())) {
                    arg = tag.substring(enumTag.getOpenTag().length());
                    if (enumTag.getArgument().equalsIgnoreCase(arg)) {
                        // System.out.println("checkValid -> return true");
                        return true;
                    }
                    // System.out.println("checkValid -> return false");
                    return false;
                }
            } else if (enumTag.getOpenTag().equalsIgnoreCase(tag)
                    || enumTag.getCloseTag().equalsIgnoreCase(tag)) {
                // System.out.println("checkValid -> return true");
                return true;
            }
        }
        System.out.println("checkValid -> return false");
        return false;
    }

    /**
     * addLeafNode(String tag, String data) Takes any text as a string that is
     * present between valid tags and creates a leaf node with that data as a
     * child of the tag in which it is nested.
     */
    private void addLeafNode(String name) {
        if (current == null) {
            current = new HTMLLeaf(name, "", null);
            return;
        }
        HTMLLeaf newNode = new HTMLLeaf(name, "", current);
        current.add(newNode);
        // System.out.println("Adding LeafNode: " + newNode.getName());
    }

    /**
     * addHTMLNode(String tag) Takes a valid 'opening' tag and creates a node
     * for it and connects that node as a child to the current node. If the node
     * is the first node, it will set parent to 'null'.
     */
    private void addHTMLNode(String tag) {
        if (count == 0) {
            HTMLComponent newNode = new HTMLComposite(tag, "unclosed", null);
            current = newNode;
            // System.out.println("Adding Root node: " + current.getName());
            count++;
        } else {
            HTMLComponent newNode = new HTMLComposite(tag, "unclosed", current);
            current.add(newNode);
            current = newNode;
            // System.out.println("Adding Node: " + current.getName());
            count++;
        }
    }

    /**
     * Takes a valid 'closing' tag and checks to see if the parent tag matches
     * it. If not, returns an error to the user and sets well-formed to false
     * for the document. If it does match, it sets the current to the parent
     * node for further processing.
     * 
     * @return false if tag does not match current tag, true if it does.
     */
    private boolean checkCloseTag(String tag) {
        if (count == 0) // if the first tag encountered is a close tag
            return false;
        if (tag.compareTo(current.getName()) != 0) {
            return false;
        } else {
            if (current.parent != null) {
                current.setCloseTag(tag);
                current = current.parent;
            }
            return true;
        }
    }
}
