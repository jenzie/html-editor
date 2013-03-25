package se362.composite;

import java.io.File;

import se362.gui.GUI;

/**
 * HTMLCheck.
 * For parsing file for HTML tags and building composite tree.
 * @author Chad Koppes, chadkoppes@gmail.com
 */

public class HTMLCheck {
	
		GUI editor;
		HTMLComponent current;
		int count = 0;
		String file;

		public HTMLCheck(GUI editor) {
			this.editor = editor;
		}
		
		public void doCheck() {
		//	file = editor.getActiveFileWindow().getAllText();  //TODO: ensure getAllText() works on next push.
			
			//run through each line of file
			//when it encounters a tag
			//call checkValid(), if the tag is invalid, continue parse, otherwise:
			//if 'open' tag:
			//addHTMLNode()
			//if 'close' tag:
			//checkCloseTag()
			//at end of parse check current - if parent != null - then tree is wrong!
		}
		
		public boolean checkValid(String tag) {
			// if tag is valid
			return true;
		}
		
		public String tagParse(String tag) {
			tag.replace("<", "");
			tag.replace(">", "");
			tag.replace("/", "");
			return tag;
		}
		
		public void addHTMLNode(String tag) {
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
		
		public void checkCloseTag(String tag) {
			String cleanTag = tagParse(tag);
			
			if(cleanTag.compareTo(current.getName()) != 0) {
				//return error message!  TREE IS WRONG!
			} else {
				current = current.parent;
			}
		}
		
		
		
}
