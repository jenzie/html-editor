/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch
 *
 */
public class IndentAllCommand implements Command {
	Indent indent;
	
	public IndentAllCommand (Indent indent){
		this.indent = indent;
	}
	@Override
	public void execute() {
		indent.indentAll();
	}

}
