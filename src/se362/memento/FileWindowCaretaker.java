package se362.memento;

import java.util.ArrayList;

public class FileWindowCaretaker {

    ArrayList<FileWindowMemento> savedStates;

    public FileWindowCaretaker() {
        savedStates = new ArrayList<FileWindowMemento>();
    }

    public void addMemento(FileWindowMemento newState) {
        savedStates.add(newState);
    }

    public FileWindowMemento retrieveMemento() {
        if (savedStates.size() > 0) {
            FileWindowMemento item = savedStates.remove(savedStates.size() - 1);
            return item;
        } else {
            return null;
        }

    }

}
