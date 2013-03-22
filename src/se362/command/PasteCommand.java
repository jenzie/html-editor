package se362.command;

/*
 * Command Object for Paste command
 * @author Brad Bensch, brb7020@g.rit.edu
 * 
 */

public class PasteCommand implements Command{
	Paste paste;
	
	public PasteCommand(Paste paste){
		this.paste = paste;
	}
	
	@Override
	public void execute() {
		paste.doPaste();
		
	}

}
