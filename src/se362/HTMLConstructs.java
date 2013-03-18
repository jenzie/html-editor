package se362;

public enum HTMLConstructs {
    HTML("<HTML>", "</HTML>");
    
    private final String openTag;
    private final String closeTag;
    
    HTMLConstructs(String open, String close) {
        this.openTag = open;
        this.closeTag = close;
    }
    
    public String getOpenTag() {
        return this.openTag;
    }
    
    public String getCloseTag() {
        return this.closeTag;
    }
}
