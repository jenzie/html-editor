/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch
 * 
 */
public class SaveAsCommand implements Command {
    SaveAs saveAs;

    public SaveAsCommand(SaveAs saveAs) {
        this.saveAs = saveAs;
    }

    @Override
    public void execute() {
        saveAs.doSave();

    }

}
