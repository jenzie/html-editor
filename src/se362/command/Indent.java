package se362.command;

import se362.gui.GUI;

/*
 * Indent Functionality: Indents selected text
 */
public class Indent {
        private GUI editor;
        
        public Indent(GUI editor){
                this.editor = editor;
        }
        
        public void indentSelected(){
                if (editor.getActiveFileWindow().getSelectedText() != null){
                        String selected = editor.getActiveFileWindow().getSelectedText();
                        selected = selected.replaceAll("\n", "\n" + editor.Spaces());
                        int start = editor.getActiveFileWindow().
                                        getTextArea().getSelectionStart();
                        int end = editor.getActiveFileWindow().
                                        getTextArea().getSelectionEnd();
            
                        editor.getActiveFileWindow().getTextArea().
                                        replaceRange(selected, start, end);
                        
                        int newLine = editor.getActiveFileWindow()
                                        .getAllText().indexOf("\n");
                        
                        if (start < newLine){
                                prependText();
                        }
                        else if(newLine == -1){
                                prependText();
                        }
                }
                else{
                        int cursor = editor.getActiveFileWindow().
                                        getTextArea().getCaretPosition();
                        
                        String subString = editor.getActiveFileWindow().getTextArea()
                                        .getText().substring(0, cursor);
                        Integer index = subString.lastIndexOf("\n");
                        if (index != -1){
                                editor.getActiveFileWindow().getTextArea().
                                                replaceRange("\n" + editor.Spaces(), index, index + 1);
                        }
                        else{
                                prependText();
                        }
                }
        }
        
        public void indentAll(){               
                String text = editor.getActiveFileWindow().getAllText();
                
                text = text.replaceAll("\n","\n" + editor.Spaces());
                text = editor.Spaces() + text;
                
                editor.getActiveFileWindow().getTextArea().setText(text);
        }
        
        
        private void prependText(){
                String text = editor.getActiveFileWindow().getAllText();
                text = editor.Spaces() + text;
                editor.getActiveFileWindow().setTextArea(text);
        }
        
}