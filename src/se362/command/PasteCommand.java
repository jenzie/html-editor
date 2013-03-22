package se362.command;

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
