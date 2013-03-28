package se362.command;

import se362.composite.HTMLCheck;
import se362.gui.GUI;

public class TextFunctions {
    public static final int SAVE = 0;
    public static final int COPY = 1;
    public static final int CUT = 2;
    public static final int PASTE = 3;
    public static final int NEW = 4;
    public static final int CHECK = 5;
    public static final int SAVEAS = 6;
    public static final int OPEN = 7;
    public static final int INDENTAll = 8;
    public static final int INDENTSELECTED = 9;
    public static final int INSERT = 10;
    
    private GUI editor;

	Command[] edit;
	
	//Constructor
	public TextFunctions(GUI editor){
		this.editor = editor;
		SaveAs saveAs = new SaveAs(editor);
		Indent indent = new Indent(editor);

		edit = new Command[11];
		edit[0] = new SaveCommand(editor, new Save(editor), saveAs);
		edit[1] = new CopyCommand(new Copy(editor));
		edit[2] = new CutCommand(new Cut(editor));
		edit[3] = new PasteCommand(new Paste(editor));
		edit[4] = new NewFileCommand(new NewFile(editor));
		edit[5] = new HTMLCheckerCommand(new HTMLCheck(editor));
		edit[6] = new SaveAsCommand(saveAs);
		edit[7] = new OpenCommand(new Open(editor));
		edit[8] = new IndentAllCommand(indent);
		edit[9] = new IndentSelectedCommand(indent);
		edit[10] = new InsertCommand(new Insert(editor));

	}
	
	/*
	 * When a button is pushed or a dropdown item selected
	 * execute the functionality for the option
	 */
	public void actionEvent(int spot){
		edit[spot].execute();
	}
	
}