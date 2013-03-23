package se362.command;

public class TextFunctions {
	Command[] edit;
	
	//Constructor
	public TextFunctions(){
		edit = new Command[5];
		edit[0] = new SaveCommand(new Save());
		edit[1] = new CopyCommand(new Copy());
		edit[2] = new CutCommand(new Cut());
		edit[3] = new PasteCommand(new Paste());
		edit[4] = new NewFileCommand(new newFile());
		edit[5] = new HTMLCheckerCommand(new HTMLChecker());
		//TODO: Add New file Command
		//TODO: Add HTMLcheck Command
	}
	
	/*
	 * When a button is pushed or a dropdown item selected, execute the functionality for the option
	 */
	public void actionEvent(int spot){
		edit[spot].execute();
	}
	
}