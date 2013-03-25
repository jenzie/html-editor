/**
 * 
 */
package se362.command;

import java.io.File;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 *
 */
public class SaveAs{
	GUI editor;
	File file;
	
	public SaveAs(GUI editor){
		this.editor = editor;
	}
	
	public void doSave(){
		editor.getFileChooser().showSaveDialog(editor); 
			//Returns an Int on cancel/save/error
	}
}
