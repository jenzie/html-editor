package se362.memento;

import java.util.ArrayList;

public class FileWindowCaretaker {
	
	ArrayList<FileWindowMemento> savedStates;
	
	public FileWindowCaretaker(){
		savedStates = new ArrayList<FileWindowMemento>();
	}
	
	public void addMemento(FileWindowMemento newState){
		savedStates.add(newState);
	}
	
}	
