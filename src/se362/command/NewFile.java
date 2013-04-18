/**
 * 
 */
package se362.command;

import se362.gui.GUI;

/**
 * @author Brad Bensch
 * 
 */
public class NewFile {
    private GUI editor;

    public NewFile(GUI editor) {
        this.editor = editor;
    }

    public void doMake() {
        /*
         * Open new blank file
         */

        editor.newTab();

    }

}
