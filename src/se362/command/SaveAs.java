/**
 * 
 */
package se362.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 * 
 */
public class SaveAs {
    GUI editor;
    File file;

    public SaveAs(GUI editor) {
        this.editor = editor;
    }

    public void doSave() {
        JFileChooser fc = editor.getFileChooser();
        int choice = fc.showSaveDialog(editor);
        if (choice == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(file);
                editor.getActiveFileWindow().getTextArea().write(writer);
            } catch (IOException e) {
            }
        }
        // Returns an Int on cancel/save/error
    }
}
