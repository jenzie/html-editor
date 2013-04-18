/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch
 * 
 */
public class IndentSelectedCommand implements Command {
    Indent indent;

    public IndentSelectedCommand(Indent indent) {
        this.indent = indent;
    }

    @Override
    public void execute() {
        indent.indentSelected();
    }

}
