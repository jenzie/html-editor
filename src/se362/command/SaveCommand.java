package se362.command;

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
