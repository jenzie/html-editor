package se362.command;

import se362.gui.GUI;

/*
 * Indent Functionality: Indents selected text
 */
public class Indent {
	private GUI editor;
	private int spacing;
	
	public Indent(GUI editor){
		this.editor = editor;
		spacing = 5;
	}
	
	public void indentSelected(){
		String spaces = "";
		for (int x = 0; x<spacing; x++){
			spaces = spaces + " ";
		}
		if (editor.getActiveFileWindow().getSelectedText() != null){
			String selected = editor.getActiveFileWindow().getSelectedText();
			selected = selected.replaceAll("\n", "\n" + spaces);
			int start = editor.getActiveFileWindow().getTextArea().getSelectionStart();
			int end = editor.getActiveFileWindow().getTextArea().getSelectionEnd();
	    
			editor.getActiveFileWindow().getTextArea().replaceRange(selected, start, end);
			
			int newLine = editor.getActiveFileWindow()
					.getAllText().indexOf("\n");
			
			if (start < newLine){
				appendText();
			}
			else if(newLine == -1){
				appendText();
			}
		}
		else{
			int cursor = editor.getActiveFileWindow().getTextArea().getCaretPosition();
			
			String subString = editor.getActiveFileWindow().getTextArea()
					.getText().substring(0, cursor);
			Integer index = subString.lastIndexOf("\n");
			if (index != -1){
				editor.getActiveFileWindow().getTextArea().replaceRange("\n" + spaces, index, index + 1);
			}
			else{
				appendText();
			}
		}
	}
	
	public void indentAll(){
		String spaces = "";
		for (int x = 0; x<spacing; x++){
			spaces = spaces + " ";
		}
		
		String text = editor.getActiveFileWindow().getAllText();
		
		text = text.replaceAll("\n","\n" + spaces);
		text = spaces + text;
		
		editor.getActiveFileWindow().getTextArea().setText(text);
	}
	
	public void setSpacing(int spacing){
		this.spacing = spacing;
	}
	
	private void appendText()
	{
		String spaces = "";
		for (int x = 0; x<spacing; x++){
			spaces = spaces + " ";
		}
		String text = editor.getActiveFileWindow().getAllText();
		text = spaces + text;
		editor.getActiveFileWindow().setTextArea(text);
	}
	
}
