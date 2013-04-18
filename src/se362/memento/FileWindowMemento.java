package se362.memento;

import se362.composite.*;

public class FileWindowMemento {

    private HTMLComponent rootNode;
    private boolean savedState;

    public FileWindowMemento(HTMLComponent rootNode, boolean savedState) {
        this.rootNode = rootNode;
        this.savedState = savedState;
    }

    public HTMLComponent getRootNode() {
        return rootNode;
    }

    public boolean getSavedState() {
        return savedState;
    }

}
