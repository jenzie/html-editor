package se362.command;

/**
 * @author Brad Bensch
 *
 */
public class NewFileCommand implements Command{
	NewFile newFile;
	
	public NewFileCommand(NewFile newFile){
		this.newFile = newFile;
	}

	@Override
	public void execute() {
		newFile.doMake();
		
	}
	
}
