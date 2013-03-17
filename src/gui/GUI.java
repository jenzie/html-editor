package gui;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.io.File;

@SuppressWarnings("serial")
public class GUI extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem mntmOpen;
    private JMenuItem mntmSave;
    private JMenuItem mntmSaveAs;
    private JMenu editMenu;
    private JMenu insertMenu;
    private JMenuItem mntmCut;
    private JMenuItem mntmCopy;
    private JMenuItem mntmPaste;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new GUI(null);
    }

    public GUI() {
        this(null);
    }
    
    /**
     * Create the frame.
     */
    public GUI(File startFile) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 473);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
        getContentPane().setLayout(new BorderLayout());
        
        JPanel iconPane = new JPanel();
        getContentPane().add(iconPane, BorderLayout.NORTH);
        iconPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(fileMenu);

        Icon openIcon = new ImageIcon("Open.png");
        mntmOpen = new JMenuItem("Open", openIcon);
        fileMenu.add(mntmOpen);
        
        mntmSave = new JMenuItem("Save");
        fileMenu.add(mntmSave);
        
        mntmSaveAs = new JMenuItem("Save As");
        fileMenu.add(mntmSaveAs);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        setJMenuBar(menuBar);
        
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        
        mntmCut = new JMenuItem("Cut");
        editMenu.add(mntmCut);
        
        mntmCopy = new JMenuItem("Copy");
        editMenu.add(mntmCopy);
        
        mntmPaste = new JMenuItem("Paste");
        editMenu.add(mntmPaste);
        
        insertMenu = new JMenu("Insert");
        menuBar.add(insertMenu);
        setVisible(true);
    }

}
