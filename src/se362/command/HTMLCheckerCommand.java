/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch, brb7020@g.rit.edu
 *
 */
public class HTMLCheckerCommand implements Command{
	HTMLChecker html;
	
	public HTMLCheckerCommand(HTMLChecker html){
		this.html = html;
	}
	
	@Override
	public void execute() {
		html.doCheck();
		
	}

}
