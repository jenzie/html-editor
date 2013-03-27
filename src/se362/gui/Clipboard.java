package se362.gui;

/**
 * An object to store text that is cut/copied
 * @author Kevin Mulligan, kam9115@rit.edu
 *
 */
public class Clipboard {
    private String text;
    
    public Clipboard() {
        this.text = "";
    }
    
    /**
     * Get the previously copied/cut text
     * @return text
     */
    public String getText() {
        return text;
    }
    
    /**
     * Save the given text to the clipboard
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
}
