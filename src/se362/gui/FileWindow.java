package se362.gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class FileWindow extends JPanel{
    private GUI parent;
	
	private File currentFile;
	private JTextArea textArea;
	
	//Default Constructor
	public FileWindow(GUI g){
	    this.parent = g;

	    setLayout(new BorderLayout());
	    textArea = new JTextArea();
	    currentFile = null;
	    
	    ScrollPane scroll = new ScrollPane();
	    scroll.add(textArea);
	    
	    add(scroll, BorderLayout.CENTER);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    
        //Control Character commands
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "doSaveAction");
        textArea.getActionMap().put("doSaveAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.save();
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "doNewAction");
        textArea.getActionMap().put("doNewAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.newFile();
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "doOpenAction");
        textArea.getActionMap().put("doOpenAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.open();
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "doCutAction");
        textArea.getActionMap().put("doCutAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.cut();
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "doCopyAction");
        textArea.getActionMap().put("doCopyAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.copy();
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "doPasteAction");
        textArea.getActionMap().put("doPasteAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.paste();
            }
        });

	}

	//Constructor with given filename
	public FileWindow(GUI g, File file) {
	    this(g);
	    this.currentFile = file;
	    try {
            textArea.read(new FileReader(file), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	
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

	/**
	 * Returns all the text in the editor window
	 * @return text
	 */
	public String getAllText() {
	    return textArea.getText();
	}
	
	public JTextArea getTextArea() {
	    return textArea;
	}
}
