package se362.command;

public class CutCommand implements Command{
	Cut cut;
	
	//Constructor
	public CutCommand(Cut cut){
		this.cut = cut;
	}
	
	@Override
	public void execute() {
		cut.doCut();
		
	}
}
