package se362.command;

import se362.gui.GUI;

/*
 * Cut functionality; Remove Highlighted text and store it in the clipboard
 * @author Brad Bensch, brb7020@g.rit.edus
 * 
 */
public class Cut {
	private GUI editor;
	String cut;
	
	public Cut(GUI editor){
		this.editor = editor;
	}
	
	public void doCut() {
		/*
		 * Find current selected text
		 * Save text to GUI Clipboard
		 * Remove text from TextArea
		 */
		String cut = editor.getActiveFileWindow().getTextArea().getSelectedText();
		editor.getActiveFileWindow().getTextArea().cut();
		editor.getClipboard().setText(cut);
	}
	
}
