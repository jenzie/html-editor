package se362.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.filechooser.FileFilter;

import se362.command.TextFunctions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame {
    String message;

    private JFileChooser fileChooser;
    private Clipboard clipboard;
    private TextFunctions invoker;
    
    private ArrayList<FileWindow> windows;
    private JTabbedPane tabbedPane;

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
        this.setTitle("HTML Editor");
        this.windows = new ArrayList<FileWindow>();
        if(startFile == null) {
            FileWindow window = new FileWindow(this);
            windows.add(window);
        }
        else {
            FileWindow window = new FileWindow(this, startFile);
            windows.add(window);
        }
        this.invoker = new TextFunctions(this);
        
        this.clipboard = new Clipboard();
        
        this.fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if(f.isDirectory()) {
                    return true;
                }
                String extension = getExtension(f);
                if(extension != null) {
                    if(extension.equals("html") || extension.equals("txt")) {
                        return true;
                    }
                }
                return false;
            }
            public String getDescription() {
                return "Shows only html and txt files";
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 473);
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        for(FileWindow f : windows) {
            tabbedPane.add(f);
        }
        
        getContentPane().setLayout(new BorderLayout());
        
        JPanel iconPane = new JPanel();
        getContentPane().add(iconPane, BorderLayout.NORTH);
        iconPane.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        
        JButton btnNew = new JButton(new ImageIcon(GUI.class.getResource("/new.png")));
        btnNew.setPreferredSize(new Dimension(22, 22));
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.actionEvent(TextFunctions.NEW);
            }
        });
        iconPane.add(btnNew);
        
        JButton btnOpen = new JButton(new ImageIcon(GUI.class.getResource("/open.png")));
        btnOpen.setPreferredSize(new Dimension(22, 22));
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO invoker.actionEvent(TextFunctions.OPEN);
            }
        });
        iconPane.add(btnOpen);
        
        JButton btnSave = new JButton(new ImageIcon(GUI.class.getResource("/save.png")));
        btnSave.setPreferredSize(new Dimension(22, 22));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.CHECK);
                //TODO get result of check
                if(message != null) {
                    JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
                }
                invoker.actionEvent(TextFunctions.SAVE);
            }
        });
        iconPane.add(btnSave);
        
        JButton btnCloseTab = new JButton(new ImageIcon(GUI.class.getResource("/close.png")));
        btnCloseTab.setPreferredSize(new Dimension(22, 22));
        btnCloseTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeSelectedTab();
                //TODO Check unsaved?
            }
        });
        iconPane.add(btnCloseTab);
        
        iconPane.add(new JLabel("      "));
        
        JButton btnCut = new JButton(new ImageIcon(GUI.class.getResource("/cut.png")));
        btnCut.setPreferredSize(new Dimension(22, 22));
        btnCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.actionEvent(TextFunctions.CUT);
            }
        });
        iconPane.add(btnCut);
        
        JButton btnCopy = new JButton(new ImageIcon(GUI.class.getResource("/copy.png")));
        btnCopy.setPreferredSize(new Dimension(22, 22));
        btnCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.actionEvent(TextFunctions.COPY);
            }
        });
        iconPane.add(btnCopy);
        
        JButton btnPaste = new JButton(new ImageIcon(GUI.class.getResource("/paste.png")));
        btnPaste.setPreferredSize(new Dimension(22, 22));
        btnPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.actionEvent(TextFunctions.PASTE);
            }
        });
        iconPane.add(btnPaste);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem mntmNew = new JMenuItem("New", new ImageIcon(GUI.class.getResource("/new.png")));
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.actionEvent(TextFunctions.NEW);
            }
        });
        fileMenu.add(mntmNew);
        
        JMenuItem mntmOpen = new JMenuItem("Open", new ImageIcon(GUI.class.getResource("/open.png")));
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //invoker.actionEvent(TextFunctions.OPEN);
                //TODO command
            }
        });
        fileMenu.add(mntmOpen);
        
        JMenuItem mntmSave = new JMenuItem("Save", new ImageIcon(GUI.class.getResource("/save.png")));
        mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.CHECK);
                //TODO result of check
                invoker.actionEvent(TextFunctions.SAVE);
            }
        });
        fileMenu.add(mntmSave);
        
        JMenuItem mntmSaveAs = new JMenuItem("Save As");
        fileMenu.add(mntmSaveAs);
        mntmSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.CHECK);
                //TODO result of check
                invoker.actionEvent(TextFunctions.SAVEAS);
            }
        });
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        JMenuItem mntmCloseTab = new JMenuItem("Close current tab", new ImageIcon(GUI.class.getResource("/close.png")));
        mntmCloseTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                closeSelectedTab();
                //TODO unsaved?
            }
        });
        fileMenu.add(mntmCloseTab);
        
        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO exit program
            }
        });
        fileMenu.add(mntmExit);
        
        setJMenuBar(menuBar);
        
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        
        JMenuItem mntmCut = new JMenuItem("Cut", new ImageIcon(GUI.class.getResource("/cut.png")));
        mntmCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.CUT);
            }
        });
        editMenu.add(mntmCut);
        
        JMenuItem mntmCopy = new JMenuItem("Copy", new ImageIcon(GUI.class.getResource("/copy.png")));
        mntmCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.COPY);
            }
        });
        editMenu.add(mntmCopy);
        
        JMenuItem mntmPaste = new JMenuItem("Paste", new ImageIcon(GUI.class.getResource("/paste.png")));
        mntmPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.PASTE);
            }
        });
        editMenu.add(mntmPaste);
        
        JMenuItem mntmCheckHtml = new JMenuItem("Check HTML", new ImageIcon(GUI.class.getResource("/check.png")));
        mntmCheckHtml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoker.actionEvent(TextFunctions.CHECK);
            }
        });
        editMenu.add(mntmCheckHtml);
        
        JMenu insertMenu = new JMenu("Insert");
        //TODO insert constructs
        menuBar.add(insertMenu);
        setVisible(true);
    }
    
    /**
     * Given a file, adds the file to the view
     * @param file: the file to open
     */
    public void open(File file) {
        FileWindow w = new FileWindow(this, file);
        windows.add(w);
        tabbedPane.add(w);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }
    
    /**
     * Returns the extension of the given file
     * @param f: file to check
     * @return String: the file extension
     */
    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Get the Clipboard
     * @return
     */
    public Clipboard getClipboard() {
        return clipboard;
    }
    
    /**
     * Get the active editor window
     * @return
     */
    public FileWindow getActiveFileWindow() {
        return (FileWindow) tabbedPane.getSelectedComponent();
    }
    
    /**
     * Get the file chooser for saving/opening
     * @return filechooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }
    
    public void open() {
        //invoker.actionEvent(TextFunctions.OPEN);
    }
    
    public void cut() {
        invoker.actionEvent(TextFunctions.CUT);
    }
    
    public void copy() {
        invoker.actionEvent(TextFunctions.COPY);
    }
    
    public void paste() {
        invoker.actionEvent(TextFunctions.PASTE);
    }
    
    public void newFile() {
        invoker.actionEvent(TextFunctions.NEW);
    }
    
    public void save() {
        invoker.actionEvent(TextFunctions.SAVE);
    }
    
    public void saveAs() {
        invoker.actionEvent(TextFunctions.SAVEAS);
    }
    
    public void check() {
        invoker.actionEvent(TextFunctions.CHECK);
    }
    
    public void close() {
        //TODO close program
    }
    
    public void setMessage(String s) {
        this.message = s;
    }
    
    /**
     * Opens a new, empty tab
     */
    public void  newTab() {
        FileWindow w = new FileWindow(this);
        windows.add(w);
        tabbedPane.add(w);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }
    
    /**
     * Closes the currently selected tab
     */
    public void closeSelectedTab() {
        FileWindow w = (FileWindow) tabbedPane.getSelectedComponent();
        if(w == null) return;
        int i = tabbedPane.getSelectedIndex();
        windows.remove(w);
        tabbedPane.remove(i);
        //TODO check for unsaved
        //TODO HTML check
    }
    
    

}
