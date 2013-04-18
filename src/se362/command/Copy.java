package se362.command;

import se362.gui.GUI;

/*
 * Copy Functionality: Takes highlighted text and copies it to the clipboard
 * @author Brad Bensch, brb7020@g.rit.edu
 */
public class Copy {
    private GUI editor;
    private String text;

    public Copy(GUI editor) {
        this.editor = editor;
    }

    public void doCopy() {
        /*
         * Find current selected text Save it to the GUI Clipboard
         */
        editor.getActiveFileWindow().saveState();
        text = editor.getActiveFileWindow().getTextArea().getSelectedText();
        editor.getClipboard().setText(text);

    }

}
