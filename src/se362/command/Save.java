/**
 * 
 */
package se362.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 * 
 */
public class Save {
    private GUI editor;
    private File file;

    public Save(GUI editor) {
        this.editor = editor;
    }

    public void doSave() {
        /*
         * Run HTMLCheck If true, write file to new or pre-existing location
         */

        file = editor.getActiveFileWindow().getFile();
        try {
            FileWriter writer = new FileWriter(file);
            editor.getActiveFileWindow().getTextArea().write(writer);
        } catch (IOException e) {
        }

    }

}
