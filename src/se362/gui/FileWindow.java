package se362.gui;

import javax.swing.*;

import java.io.File;

public class FileWindow extends JPanel{
	
	private File currentFile;
	private JTextArea textArea;
	
	
	public static void main(String args[]) {
	    JFrame frame = new JFrame();
	    frame.add(new FileWindow());
	    frame.setVisible(true);
	}
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
