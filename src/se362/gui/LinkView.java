package se362.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import se362.composite.HTMLComponent;

@SuppressWarnings("serial")
public class LinkView extends JFrame{
    private FileWindow window;
    
    public static void main(String[] args) {
        new LinkView(null);
    }
    
    public LinkView(FileWindow w) {
        this.window = w;
        setBounds(0, 0, 400, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent arg0) {
            }
            public void windowClosed(WindowEvent arg0) {
            }
            public void windowClosing(WindowEvent arg0) {
                close();
            }
            public void windowDeactivated(WindowEvent arg0) {                
            }
            public void windowDeiconified(WindowEvent arg0) {
            }
            public void windowIconified(WindowEvent arg0) {
            }
            public void windowOpened(WindowEvent arg0) {
            }
        });
        
        JTextArea textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
        
        //HTMLComponent node = w.getRootNode();
        //String text = node.getText();
        String text = "<html>\n<a href=\"www\">text</a>\n<a href=\"www.google.com\">text</a>\nrandom text\n<a href=\"http://www.gOogle.com\">\n</html>";
        Pattern p = Pattern.compile("href=\"([A-Za-z.:\\/-_()%]*)\"");
        Matcher m = p.matcher(text);
        while(m.find()) {
            System.out.println(text.substring(m.start(), m.end()));
            textArea.setText(textArea.getText() + text.substring(m.start(), m.end()));
        }
        
        JPanel pane = new JPanel();
        add(pane);
        
        JMenuBar menu = new JMenuBar();
        JMenuItem refresh = new JMenuItem(
			"Refresh", new ImageIcon(GUI.class.getResource("/refresh.png")));
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refresh();
            }
        });
        menu.add(refresh);
        
        setJMenuBar(menu);
        
        
        setVisible(true);
    }
    
    public void refresh() {
        //TODO refresh link view
        System.out.println("refresh");
    }
    
    public void close() {
        //window.removeLinkView(); TODO remove this from FileWindow
        
    }

}
