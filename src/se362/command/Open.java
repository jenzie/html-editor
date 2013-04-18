/**
 * 
 */
package se362.command;

import javax.swing.JFileChooser;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 * 
 */
public class Open {
    GUI editor;

    public Open(GUI editor) {
        this.editor = editor;
    }

    public void doOpen() {
        int choice = editor.getFileChooser().showOpenDialog(editor);
        if (choice == JFileChooser.APPROVE_OPTION) {
            editor.open(editor.getFileChooser().getSelectedFile());
        } // Maybe move the actual open in GUI to here?
    }

}
