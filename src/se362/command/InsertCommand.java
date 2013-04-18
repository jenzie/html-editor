/**
 * 
 */
package se362.command;

/**
 * @author Brad Bensch
 * 
 */
public class InsertCommand implements Command {
    Insert insert;

    public InsertCommand(Insert insert) {
        this.insert = insert;
    }

    @Override
    public void execute() {
        insert.doInsert();

    }

}
