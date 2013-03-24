package se362.command;

/*
 * Command object for Save class
 * @author Brad Bensch, brb7020@g.rit.edu
 */
public class SaveCommand implements Command{
	Save save;
	
	public SaveCommand(Save save){
		this.save = save;
	}

	@Override
	public void execute() {
		save.doSave();
		
	}

}
