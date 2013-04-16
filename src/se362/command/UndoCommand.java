package se362.command;

import se362.gui.GUI;

public class UndoCommand implements Command {
    private GUI editor;

    public UndoCommand(GUI editor) {
        this.editor = editor;
    }
    
    @Override
    public void execute() {
        editor.getActiveFileWindow().undo();
    }

}
