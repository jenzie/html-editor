/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch
 *
 */
public class OpenCommand implements Command{
	Open open;
	
	public OpenCommand(Open open){
		this.open = open;
	}
	
	@Override
	public void execute() {
		open.doOpen();
		
	}

	
	
}
