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
		 * Iterates through the list to check A) if it is a valid tag B) if it 
		 * is an opening tag or closing tag.
		 * Calls helper methods to check valid and to build the tree
		 * based on whether it is opening or closing tag.
		 * @return	String "success" if the tree builds successfully.
		 * "fail" if not.
		 */
		
		public void doCheck() {
			count = 0;
			System.out.println("---- STARTING CHECK ----");
			file = editor.getActiveFileWindow().getAllText();
			ArrayList<String> tagList = new ArrayList<String>();
			Pattern pattern = Pattern.compile("((<)(\\w+)(>))|((</)(\\w+)(>))");
			Matcher matcher = pattern.matcher(file);
			
			while (matcher.find()) {
//				System.out.println(matcher.group());
				tagList.add(matcher.group());
			}
			for(String tag : tagList) {
//				System.out.println("ArrayList: " + tag);
				if(!checkValid(tag)) {
					tagList.remove(tag);
				}
			}
			for(String tag : tagList) {
//				System.out.println("TagList: " + tag);
				if(tag.startsWith("</")) {
					if(count == 0) {
						editor.setMessage("HTML is NOT well-formed");
						break;
					}
					if(checkCloseTag(tag) == false) {
						editor.setMessage("HTML is not well-formed.");
						break;
					}
				} else {
					addHTMLNode(tag);
				}
			}
			if(current != null) {
				validTree();
			} else {
				editor.setMessage("HTML is not well-formed.");
			}
		}
		/**
		 * Checks to see if the current tree is a valid tree by checking to see
		 * if the current node is the root node.
		 * Sets the editor message appropriately.
		 */
		private void validTree() {
			if(current.parent == null) {
				editor.setMessage("HTML IS GREAT!");
			} else {
				editor.setMessage("HTML is not well-formed.");
			}
		}
		
		/**
		 * Checks to see if the given tag is a valid html tag.
		 * (Used before adding node of the tag to the tree.
		 * For testing purposes, it is public and static.
		 * @return	true if tag is valid html; false otherwise.
		 */
		public static boolean checkValid(String tag) {
			String open, arg;
			System.out.println("checkValid() initiated");

			// check against list of valid tags; ignore upper/lowercase
			for (HTMLConstructs enumTag : HTMLConstructs.values()) {
				if(enumTag.getArgument() != null) {
					open = tag.substring(0, enumTag.getOpenTag().length());
					if(open.equalsIgnoreCase(enumTag.getOpenTag())) {
						arg = tag.substring(enumTag.getOpenTag().length());
						if(enumTag.getArgument().equalsIgnoreCase(arg)) {
							System.out.println("checkValid -> return true");
							return true;
						}
						System.out.println("checkValid -> return false");
						return false;
					}
				} else if (enumTag.getOpenTag().equalsIgnoreCase(tag) ||
						enumTag.getCloseTag().equalsIgnoreCase(tag)) {
					System.out.println("checkValid -> return true");
					return true;
				}
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
			//System.out.println("Input tag: " + tag);
			String cleanTag1;
			String cleanTag2;
			String cleanTag3;
			cleanTag1 = tag.replace("<", "");
			cleanTag2 = cleanTag1.replace(">", "");
			cleanTag3 = cleanTag2.replace("/", "");
			//System.out.println("Clean tag: " + cleanTag3);
			return cleanTag3;
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
				//System.out.println("ROOT NODE");
				HTMLComponent newNode = new HTMLComposite(cleanTag, null);
				current = newNode;
				System.out.println("Adding Root node: " + current.getName());
				count++;
			} else {
				HTMLComponent newNode = new HTMLComposite(cleanTag, current);
				current.add(newNode);
				current = newNode;
				System.out.println("Adding Node: " + current.getName());
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
		
		private boolean checkCloseTag(String tag) {
			String cleanTag = tagParse(tag);
			if(count == 0)
				return false;
			System.out.println("Closing Tag Found: " + cleanTag + " ,Current: " + current.getName());	
			if(cleanTag.compareTo(current.getName()) != 0) {
				System.out.println("Closing tag does not match current.  Fail.");
				return false;
				//editor.setMessage("HTML is NOT well-formed.");
			} else {
				if(current.parent != null) {
					current = current.parent;
				}
				System.out.println("Closing tag matches current, new current: " + current.getName());
				return true;
			}
		}
}
