package se362.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
        
        JPanel pane = new JPanel();
        add(pane);
        
        JMenuBar menu = new JMenuBar();
        JMenuItem refresh = new JMenuItem("Refresh", new ImageIcon(GUI.class.getResource("/refresh.png")));
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
