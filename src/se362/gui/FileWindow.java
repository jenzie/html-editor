package se362.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileWindow extends JPanel{
	
	private File currentFile;
	private JTextArea textArea;
	
	
	public static void main(String args[]) {
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(new FileWindow());
	    frame.setVisible(true);
	}
	//Default Constructor
	public FileWindow(){

	    setLayout(new BorderLayout());
	    textArea = new JTextArea();
	    currentFile = null;
	    
	    ScrollPane scroll = new ScrollPane();
	    scroll.add(textArea);
	    
	    add(scroll, BorderLayout.CENTER);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);

	}

//Constructor with given filename
	public FileWindow(File file) {
	    this();
	    this.currentFile = file;
	    try {
            textArea.read(new FileReader(file), null);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
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
	
	public String getSelectedText() {
	    return textArea.getSelectedText();
	}
	
	public String cutSelectedText() {
	    String s = textArea.getSelectedText();
	    textArea.cut();
	    return s;
	}
	
	public void insertText(String text) {
	    textArea.insert(text, textArea.getSelectionStart());
	}
	
	public File getFile(){
		return currentFile;
	}


}
