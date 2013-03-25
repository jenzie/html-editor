package se362.command;

import se362.gui.GUI;

/*
 * Command object for Save class
 * @author Brad Bensch, brb7020@g.rit.edu
 */
public class SaveCommand implements Command{
	Save save;
	SaveAs saveAs;
	GUI squishy;
	
	public SaveCommand(GUI editor, Save save, SaveAs saveAs){
		this.save = save;
		this.saveAs = saveAs;
		this.squishy = editor;
	}

	@Override
	public void execute() {
		
		if(squishy.getActiveFileWindow().getFile() == null){
			saveAs.doSave();
		}
		else{
			save.doSave();
		}
		
	}

}
