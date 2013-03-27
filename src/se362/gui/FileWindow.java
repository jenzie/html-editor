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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class FileWindow extends JPanel {
    private boolean saved;
    private GUI parent;

    private File currentFile;
    private JTextArea textArea;

    // Default Constructor
    public FileWindow(GUI g) {
        this.parent = g;
        this.saved = false;

        setLayout(new BorderLayout());
        textArea = new JTextArea();
        currentFile = null;

        ScrollPane scroll = new ScrollPane();
        scroll.add(textArea);

        add(scroll, BorderLayout.CENTER);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent arg0) {
            }
            public void insertUpdate(DocumentEvent arg0) {
                saved = false;
            }
            public void removeUpdate(DocumentEvent arg0) {
                saved = false;
            }
        });

        // Control Character commands
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK),
                "doSaveAction");//CTRL-S
        textArea.getActionMap().put("doSaveAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.save();
            }
        });
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK),
                "doNewAction");//CTRL-N
        textArea.getActionMap().put("doNewAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.newFile();
            }
        });
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK),
                "doOpenAction");//CTRL-O
        textArea.getActionMap().put("doOpenAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.open();
            }
        });
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK),
                "doCutAction");//CTRL-X
        textArea.getActionMap().put("doCutAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.cut();
            }
        });
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK),
                "doCopyAction");//CTRL-C
        textArea.getActionMap().put("doCopyAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.copy();
            }
        });
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK),
                "doPasteAction");//CTRL-V
        textArea.getActionMap().put("doPasteAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                parent.paste();
            }
        });
    }

    // Constructor with given filename
    public FileWindow(GUI g, File file) {
        this(g);
        this.saved = true;
        this.currentFile = file;
        try {
            textArea.read(new FileReader(file), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the currently selected text in the text area
     * 
     * @return text
     */
    public String getSelectedText() {
        return textArea.getSelectedText();
    }

    /**
     * Deletes and returns the currently selected text
     * 
     * @return text
     */
    public String cutSelectedText() {
        String s = textArea.getSelectedText();
        textArea.cut();
        return s;
    }

    /**
     * Inserts the given text into the text area at cursor position
     * 
     * @param text
     */
    public void insertText(String text) {
        textArea.insert(text, textArea.getSelectionStart());
    }

    /**
     * Returns this window's file
     * 
     * @return file
     */
    public File getFile() {
        return currentFile;
    }

    /**
     * Returns all the text in the editor window
     * 
     * @return text
     */
    public String getAllText() {
        return textArea.getText();
    }

    /**
     * getter for this window's text area
     * @return JTextArea
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Check if this file has been modified or not
     * @return boolean
     */
    public boolean isSaved() {
        return saved;
    }
}