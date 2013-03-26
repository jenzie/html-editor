package se362.composite;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se362.HTMLConstructs;
import se362.gui.GUI;

/**
 * HTMLCheck.
 * For parsing file for HTML tags and building composite tree.
 * @author Chad Koppes, chadkoppes@gmail.com
 * @author Jenny Zhen, coffee@csh.rit.edu
 */

public class HTMLCheck {
	
		private GUI editor;
		private HTMLComponent current;
		private int count = 0;
		private String file; 

		public HTMLCheck(GUI editor) {
			this.editor = editor;
		}
		
		/**
		 * doCheck()
		 * Builds an ArrayList of all tags contained in the file document.
		 * Iterates through the list to check A) if it is valid B) if it is 
		 * an opening tag or closing tag.
		 * Calls helper methods to check valid and to build the tree
		 * based on whether it is opening or closing tag.
		 * @return	String "success" if the tree builds successfully.
		 * "fail" if not.
		 */
		
		public void doCheck() {
			file = editor.getActiveFileWindow().getAllText();
			ArrayList<String> tagList = new ArrayList<String>();
			Pattern pattern = Pattern.compile("(<.)(\\w+)(>)");
			Matcher matcher = pattern.matcher(file);
			
			while (matcher.find()) {
				System.out.println(matcher.group());
				tagList.add(matcher.group());
			}
			for(String tag : tagList) {
				System.out.println("ArrayList: " + tag);
				if(!checkValid(tag)) {
					tagList.remove(tag);
				}
			}
			for(String tag : tagList) {
				System.out.println("TagList: " + tag);
				if(tag.startsWith("</")) {
					checkCloseTag(tag);
				} else {
					addHTMLNode(tag);
				}
			}
			if(current == null) {
				editor.setMessage(null);
			} else {
				editor.setMessage("HTML is not well-formed.");
			}
		}
		
		/**
		 * Checks to see if the given tag is a valid html tag.
		 * (Used before adding node of the tag to the tree.
		 * @return	true if tag is valid html; false otherwise.
		 */
		private boolean checkValid(String tag) {
			System.out.println("checkValid() initiated");
			// check against list of valid tags; ignore upper/lowercase
			for (HTMLConstructs enumTag : HTMLConstructs.values()) {
				if (enumTag.name().equalsIgnoreCase(tag))
					System.out.println("checkValid -> return true");
					return true;
			}
			System.out.println("checkValid -> return false");
			return false;
		}
		
		/**
		 * tagParse(String tag) takes a string representation of a tag
		 * and strips off the "<", ">" and "/" characters.
		 * @return	String tag without brackets.
		 */
		private String tagParse(String tag) {
			tag.replace("<", "");
			tag.replace(">", "");
			tag.replace("/", "");
			return tag;
		}
		
		/**
		 * addHTMLNode(String tag)
		 * Takes a valid 'opening' tag and creates a node for it and connects
		 * that node as a child to the current node.
		 * If the node is the first node, it will set parent to 'null'.
		 */
		private void addHTMLNode(String tag) {
			String cleanTag = tagParse(tag);
			
			if(count == 0) {
				HTMLComponent newNode = new HTMLComposite(cleanTag, null);
				current = newNode;
				count++;
			} else {
				HTMLComponent newNode = new HTMLComposite(cleanTag, current);
				current.add(newNode);
				current = newNode;
				count++;
			}
		}
		
		/**
		 * Takes a valid 'closing' tag and checks to see if the parent
		 * tag matches it.  If not, returns an error to the user and 
		 * sets well-formed to false for the document.
		 * If it does match, it sets the current to the parent node for
		 * further processing.
		 */
		private void checkCloseTag(String tag) {
			String cleanTag = tagParse(tag);
			
			if(cleanTag.compareTo(current.getName()) != 0) {
				editor.setMessage("HTML is not well-formed.");
			} else {
				current = current.parent;
			}
		}
}
