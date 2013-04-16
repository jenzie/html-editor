/**
 * 
 */
package se362.command;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 *
 */
public class Insert {
	GUI editor;
	
	public Insert(GUI editor){
		this.editor = editor;
	}
	
	public void doInsert(){
		editor.getActiveFileWindow().insertText(editor.getText());
		editor.getActiveFileWindow().saveState();
	}
	
	
}
