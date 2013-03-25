package se362.command;

import se362.composite.HTMLCheck;

/**
 * @author Brad Bensch, brb7020@g.rit.edu
 *
 */
public class HTMLCheckerCommand implements Command{
	HTMLCheck html;
	
	public HTMLCheckerCommand(HTMLCheck html){
		this.html = html;
	}
	
	@Override
	public void execute() {
		html.doCheck();
		
	}

}
