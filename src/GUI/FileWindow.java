package gui;

import javax.swing.*;

import java.io.File;

public class FileWindow {
	
	private File currentFile;
	private JTextArea textArea;
	
	//Default Constructor
	public FileWindow(){

	JPanel newPanel = new JPanel();
	textArea = new JTextArea();
	currentFile = null;
	
	newPanel.add(textArea);
	textArea.setWrapStyleWord(true);
	textArea.setLineWrap(true);

	}

//Constructor with given filename
	public FileWindow(File file) {
	
	JPanel newPanel = new JPanel();
	textArea = new JTextArea();
	currentFile = file;
	
	
	newPanel.add(textArea);
	textArea.setWrapStyleWord(true);
	textArea.setLineWrap(true);
	
	}

/* Save Method
 * 
 *  Calls HTMLCheck
 *  Writes the file if check passes
 * 
 */
	public void save(){
	
	//TODO: Figure out plan for sending content
	
}

   /* Terminate Method
 	* What the program does upon closing
 	* 
 	*/
	public void terminate(){
		
		
		
	}
	
	public File getFile(){
		return currentFile;
	}


}
