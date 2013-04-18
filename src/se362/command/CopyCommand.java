package se362.command;

public class CopyCommand implements Command {
    Copy copy;

    public CopyCommand(Copy copy) {
        this.copy = copy;
    }

    @Override
    public void execute() {
        copy.doCopy();
    }
}
