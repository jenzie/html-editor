package se362.gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileWindow extends JPanel{
	
	private File currentFile;
	private JTextArea textArea;
	
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	
	}

    /** Save Method
     * 
     *  Calls HTMLCheck
     *  Writes the file if check passes
     * 
     */
	public void save(){
	
	//TODO: Figure out plan for sending content
	
	}

   /** Terminate Method
 	* What the program does upon closing
 	* 
 	*/
	public void terminate(){
		
		
		
	}
	
	/**
	 * Returns the currently selected text in the text area
	 * @return text
	 */
	public String getSelectedText() {
	    return textArea.getSelectedText();
	}
	
	/**
	 * Deletes and returns the currently selected text
	 * @return text
	 */
	public String cutSelectedText() {
	    String s = textArea.getSelectedText();
	    textArea.cut();
	    return s;
	}
	
	/**
	 * Inserts the given text into the text area at cursor position
	 * @param text
	 */
	public void insertText(String text) {
	    textArea.insert(text, textArea.getSelectionStart());
	}
	
	/**
	 * Returns this window's file
	 * @return file
	 */
	public File getFile(){
		return currentFile;
	}


}
