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
	
	public FileWindowMemento retrieveMemento(){
		if(savedStates.size() > 0){
			FileWindowMemento item = savedStates.get(0);
			savedStates.remove(0);
			return item;
		}else{
			return null;
		}
		
	}
	
}	
