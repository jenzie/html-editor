package se362.command;

import se362.gui.GUI;

/*
 * Paste Functionality; Takes from the clipboard and adds the 
 * saved text at the cursor
 * @author Brad Bensch, brb7020@g.rit.edu
 * 
 */
public class Paste {
	private GUI editor;
	private String text;
	
	public Paste(GUI editor){
		this.editor = editor;
	}

	public void doPaste(){
		/*
		 * Take information from GUI Clipboard and
		 * insert it at current text location
		 */
		
		text = editor.getClipboard().getText();
		editor.getActiveFileWindow().getTextArea().insert(text, editor.getActiveFileWindow().getTextArea().getSelectionStart());
	}
	
}
