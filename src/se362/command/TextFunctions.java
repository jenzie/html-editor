package se362.command;

import se362.gui.GUI;

public class TextFunctions {
    public static final int SAVE = 0;
    public static final int COPY = 1;
    public static final int CUT = 2;
    public static final int PASTE = 3;
    public static final int NEW = 4;
    public static final int CHECK = 5;
    
    public GUI editor;

	Command[] edit;
	
	//Constructor
	public TextFunctions(GUI editor){
		this.editor = editor;

		edit = new Command[5];
		edit[0] = new SaveCommand(new Save(editor));
		edit[1] = new CopyCommand(new Copy(editor));
		edit[2] = new CutCommand(new Cut(editor));
		edit[3] = new PasteCommand(new Paste(editor));
		edit[4] = new NewFileCommand(new NewFile(editor));
		edit[5] = new HTMLCheckerCommand(new HTMLChecker(editor));
	}
	
	/*
	 * When a button is pushed or a dropdown item selected, execute the functionality for the option
	 */
	public void actionEvent(int spot){
		edit[spot].execute();
	}
	
}