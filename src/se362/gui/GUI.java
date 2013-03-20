package se362.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

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
    private JButton btnOpen;
    private JButton btnSave;
    private JButton btnCut;
    private JButton btnCopy;
    private JButton btnPaste;
    
    private ArrayList<FileWindow> windows;

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
        this.windows = new ArrayList<FileWindow>();
        if(startFile == null) {
            FileWindow window = new FileWindow();
            windows.add(window);
        }
        else {
            FileWindow window = new FileWindow(startFile);
            windows.add(window);
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 473);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        for(FileWindow f : windows) {
            tabbedPane.add(f);
        }
        
        getContentPane().setLayout(new BorderLayout());
        
        JPanel iconPane = new JPanel();
        getContentPane().add(iconPane, BorderLayout.NORTH);
        iconPane.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        
        btnOpen = new JButton(new ImageIcon(GUI.class.getResource("/open.png")));
        btnOpen.setPreferredSize(new Dimension(22, 22));
        iconPane.add(btnOpen);
        
        btnSave = new JButton(new ImageIcon(GUI.class.getResource("/save.png")));
        btnSave.setPreferredSize(new Dimension(22, 22));
        iconPane.add(btnSave);
        
        btnCut = new JButton(new ImageIcon(GUI.class.getResource("/cut.png")));
        btnCut.setPreferredSize(new Dimension(22, 22));
        iconPane.add(btnCut);
        
        btnCopy = new JButton(new ImageIcon(GUI.class.getResource("/copy.png")));
        btnCopy.setPreferredSize(new Dimension(22, 22));
        iconPane.add(btnCopy);
        
        btnPaste = new JButton(new ImageIcon(GUI.class.getResource("/paste.png")));
        btnPaste.setPreferredSize(new Dimension(22, 22));
        iconPane.add(btnPaste);
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(fileMenu);

        mntmOpen = new JMenuItem("Open", new ImageIcon(GUI.class.getResource("/open.png")));
        fileMenu.add(mntmOpen);
        
        mntmSave = new JMenuItem("Save", new ImageIcon(GUI.class.getResource("/save.png")));
        fileMenu.add(mntmSave);
        
        mntmSaveAs = new JMenuItem("Save As");
        fileMenu.add(mntmSaveAs);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        setJMenuBar(menuBar);
        
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        
        mntmCut = new JMenuItem("Cut", new ImageIcon(GUI.class.getResource("/cut.png")));
        editMenu.add(mntmCut);
        
        mntmCopy = new JMenuItem("Copy", new ImageIcon(GUI.class.getResource("/copy.png")));
        editMenu.add(mntmCopy);
        
        mntmPaste = new JMenuItem("Paste", new ImageIcon(GUI.class.getResource("/paste.png")));
        editMenu.add(mntmPaste);
        
        insertMenu = new JMenu("Insert");
        menuBar.add(insertMenu);
        setVisible(true);
    }

}
