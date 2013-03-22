package se362.command;

/*
 * Command object for Cut class
 * @author Brad Bensch, brb7020@g.rit.edu
 */

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
