package se362.gui;

public class Clipboard {
    private String text;
    
    public Clipboard() {
        this.text = "";
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

}
