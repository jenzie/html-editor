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

import se362.HTMLConstructs;
import se362.command.TextFunctions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The primary GUI for the HTML Editor program
 * 
 * @author Kevin Mulligan, kam9115@rit.edu
 * 
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    String message;

    private JFileChooser fileChooser;
    private Clipboard clipboard;
    private TextFunctions invoker;

    private ArrayList<FileWindow> windows;
    private JTabbedPane tabbedPane;
    private String text;

    /**
     * Launch the HTML Editor.
     */
    public static void main(String[] args) {
        new GUI(null);
    }

    public GUI() {
        this(null);
    }

    /**
     * Primary Constructor
     */
    public GUI(File startFile) {
        this.setTitle("HTML Editor");
        this.windows = new ArrayList<FileWindow>();
        if (startFile == null) {
            FileWindow window = new FileWindow(this);
            windows.add(window);
        } else {
            FileWindow window = new FileWindow(this, startFile);
            windows.add(window);
        }
        this.invoker = new TextFunctions(this);//Command receiver
        this.clipboard = new Clipboard();
        this.fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String extension = getExtension(f);
                if (extension != null) {
                    if (extension.equals("html") || extension.equals("txt")) {
                        return true;
                    }
                }
                return false;
            }
            public String getDescription() {
                return "*.html, *.txt";
            }
        });

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
        setBounds(100, 100, 870, 473);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        for (FileWindow f : windows) {
            tabbedPane.add(f);
        }

        getContentPane().setLayout(new BorderLayout());

        JPanel iconPane = new JPanel();
        getContentPane().add(iconPane, BorderLayout.NORTH);
        iconPane.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));

        //Buttons (below menubar, icons only)
        JButton btnNew = new JButton(new ImageIcon(
                GUI.class.getResource("/new.png")));
        btnNew.setPreferredSize(new Dimension(22, 22));
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                newFile();
            }
        });
        iconPane.add(btnNew);

        JButton btnOpen = new JButton(new ImageIcon(
                GUI.class.getResource("/open.png")));
        btnOpen.setPreferredSize(new Dimension(22, 22));
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                open();
            }
        });
        iconPane.add(btnOpen);

        JButton btnSave = new JButton(new ImageIcon(
                GUI.class.getResource("/save.png")));
        btnSave.setPreferredSize(new Dimension(22, 22));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = null;
                check();// null for success, string for error
                if (message != null) {
                    int choice = JOptionPane.showConfirmDialog(null, message
                            + " Save anyway?", "", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        save();
                    }
                } else {
                    save();
                }
            }
        });
        iconPane.add(btnSave);

        JButton btnCloseTab = new JButton(new ImageIcon(
                GUI.class.getResource("/close.png")));
        btnCloseTab.setPreferredSize(new Dimension(22, 22));
        btnCloseTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileWindow w = getActiveFileWindow();
                if (!w.isSaved()) {
                    int choice = JOptionPane.showConfirmDialog(null,
                            "File unsaved. Discard changes?", "",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        closeSelectedTab();
                    } else if (choice == JOptionPane.NO_OPTION) {
                        message = null;
                        check();
                        if (message != null) {
                            int confirm = JOptionPane.showConfirmDialog(null,
                                    message + " Save anyway?", "",
                                    JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                save();
                                closeSelectedTab();
                            }
                            return;
                        } else {
                            save();
                            closeSelectedTab();
                        }
                    }
                } else {
                    closeSelectedTab();
                }
            }
        });
        iconPane.add(btnCloseTab);

        iconPane.add(new JLabel("      "));//For spacing

        JButton btnCut = new JButton(new ImageIcon(
                GUI.class.getResource("/cut.png")));
        btnCut.setPreferredSize(new Dimension(22, 22));
        btnCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cut();
            }
        });
        iconPane.add(btnCut);

        JButton btnCopy = new JButton(new ImageIcon(
                GUI.class.getResource("/copy.png")));
        btnCopy.setPreferredSize(new Dimension(22, 22));
        btnCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                copy();
            }
        });
        iconPane.add(btnCopy);

        JButton btnPaste = new JButton(new ImageIcon(
                GUI.class.getResource("/paste.png")));
        btnPaste.setPreferredSize(new Dimension(22, 22));
        btnPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                paste();
            }
        });
        iconPane.add(btnPaste);

        //Menubar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //File menu items
        JMenuItem mntmNew = new JMenuItem("New", new ImageIcon(
                GUI.class.getResource("/new.png")));
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                newFile();
            }
        });
        fileMenu.add(mntmNew);

        JMenuItem mntmOpen = new JMenuItem("Open", new ImageIcon(
                GUI.class.getResource("/open.png")));
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                open();
            }
        });
        fileMenu.add(mntmOpen);

        JMenuItem mntmSave = new JMenuItem("Save", new ImageIcon(
                GUI.class.getResource("/save.png")));
        mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = null;
                check();// null for success, string for error
                if (message != null) {
                    int choice = JOptionPane.showConfirmDialog(null, message
                            + " Save anyway?", "", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        save();
                    }
                } else {
                    save();
                }
            }
        });
        fileMenu.add(mntmSave);

        JMenuItem mntmSaveAs = new JMenuItem("Save As");
        fileMenu.add(mntmSaveAs);
        mntmSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = null;
                check();// null for success, string for error
                if (message != null) {
                    int choice = JOptionPane.showConfirmDialog(null, message
                            + " Save anyway?", "", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        saveAs();
                    }
                } else {
                    saveAs();
                }
            }
        });
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JMenuItem mntmCloseTab = new JMenuItem("Close current tab",
                new ImageIcon(GUI.class.getResource("/close.png")));
        mntmCloseTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FileWindow w = getActiveFileWindow();
                if (!w.isSaved()) {
                    int choice = JOptionPane.showConfirmDialog(null,
                            "File unsaved. Discard changes?", "",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        closeSelectedTab();
                    } else if (choice == JOptionPane.NO_OPTION) {
                        message = null;
                        check();
                        if (message != null) {
                            int confirm = JOptionPane.showConfirmDialog(null,
                                    message + " Save anyway?", "",
                                    JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                save();
                                closeSelectedTab();
                            }
                            return;
                        } else {
                            save();
                            closeSelectedTab();
                        }
                    }
                } else {
                    closeSelectedTab();
                }
            }
        });
        fileMenu.add(mntmCloseTab);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        fileMenu.add(mntmExit);

        setJMenuBar(menuBar);

        //Edit menu items
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem mntmCut = new JMenuItem("Cut", new ImageIcon(
                GUI.class.getResource("/cut.png")));
        mntmCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cut();
            }
        });
        editMenu.add(mntmCut);

        JMenuItem mntmCopy = new JMenuItem("Copy", new ImageIcon(
                GUI.class.getResource("/copy.png")));
        mntmCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                copy();
            }
        });
        editMenu.add(mntmCopy);

        JMenuItem mntmPaste = new JMenuItem("Paste", new ImageIcon(
                GUI.class.getResource("/paste.png")));
        mntmPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paste();
            }
        });
        editMenu.add(mntmPaste);

        JMenuItem mntmCheckHtml = new JMenuItem("Check HTML", new ImageIcon(
                GUI.class.getResource("/check.png")));
        mntmCheckHtml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = null;
                check();
                if(message != null) {
                    JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        editMenu.add(mntmCheckHtml);
        
        JMenuItem mntmIndent = new JMenuItem("Indent");
        mntmIndent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indentSelected();
            }
        });
        editMenu.add(mntmIndent);
        
        JMenuItem mntmIndentAll = new JMenuItem("Indent All");
        mntmIndentAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indentAll();
            }
        });
        editMenu.add(mntmIndentAll);

        //insert menu items
        JMenu insertMenu = new JMenu("Insert");
        // TODO Insert for a, img, table/list
        HTMLConstructs[] tags = HTMLConstructs.values();
        for (HTMLConstructs h : tags) {
            final JMenuItem item = new JMenuItem(h.name());
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    HTMLConstructs tag = HTMLConstructs.valueOf(item.getText());
                    text = tag.getOpenTag() + tag.getCloseTag();
                    invoker.actionEvent(TextFunctions.INSERT);
                }
            });
            insertMenu.add(item);
        }
        menuBar.add(insertMenu);
        setVisible(true);
    }

    /**
     * Given a file, adds the file to the view
     * 
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
     * 
     * @param f: file to check
     * @return String: the file extension
     */
    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * Get the Clipboard
     * 
     * @return
     */
    public Clipboard getClipboard() {
        return clipboard;
    }

    /**
     * Get the HTMLText
     * 
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Get the active editor window
     * 
     * @return
     */
    public FileWindow getActiveFileWindow() {
        return (FileWindow) tabbedPane.getSelectedComponent();
    }

    /**
     * Get the file chooser for saving/opening
     * 
     * @return filechooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * calls open command in invoker
     */
    public void open() {
        invoker.actionEvent(TextFunctions.OPEN);
    }

    /**
     * calls cut command in invoker
     */
    public void cut() {
        invoker.actionEvent(TextFunctions.CUT);
    }

    /**
     * calls copy command in invoker
     */
    public void copy() {
        invoker.actionEvent(TextFunctions.COPY);
    }

    /**
     * calls paste command in invoker
     */
    public void paste() {
        invoker.actionEvent(TextFunctions.PASTE);
    }

    /**
     * calls new command in invoker
     */
    public void newFile() {
        invoker.actionEvent(TextFunctions.NEW);
    }

    /**
     * calls save command in invoker
     */
    public void save() {
        invoker.actionEvent(TextFunctions.SAVE);
        getActiveFileWindow().save();
    }

    /**
     * Calls save as command in invoker
     */
    public void saveAs() {
        invoker.actionEvent(TextFunctions.SAVEAS);
    }

    /**
     * Calls check command in invoker
     */
    public void check() {
        invoker.actionEvent(TextFunctions.CHECK);
    }
    
    /**
     * Calls indent all command in invoker
     */
    public void indentAll() {
    	invoker.actionEvent(TextFunctions.INDENTAll);
    }
    
    /**
     * Calls indent selected command in invoker
     */
    public void indentSelected(){
    	invoker.actionEvent(TextFunctions.INDENTSELECTED);
    }

    /**
     * Closes the program, first closing all tabs, checking for well formed HTML
     * and saving if selected
     */
    public void close() {
        boolean close = true;
        for (FileWindow w : windows) {
            tabbedPane.setSelectedComponent(w);
            if (!w.isSaved()) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "File unsaved. Discard changes?", "",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    tabbedPane.remove(w);
                } else if (choice == JOptionPane.NO_OPTION) {
                    message = null;
                    check();
                    if (message != null) {
                        int confirm = JOptionPane.showConfirmDialog(null,
                                message + " Save anyway?", "",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            save();
                            tabbedPane.remove(w);
                        }
                        close = false;
                    } else {
                        save();
                        tabbedPane.remove(w);
                    }
                } else {
                    close = false;
                }
            } else {
                tabbedPane.remove(w);
            }
        }
        if (close == true) {
            System.exit(0);
        }
        windows.clear();
        Component[] ws = tabbedPane.getComponents();
        for (Component w : ws) {
            windows.add((FileWindow) w);
        }
    }

    /**
     * Method for setting dialog messages used as return for HTML check
     * 
     * @param String
     *            : message to set to
     */
    public void setMessage(String s) {
        this.message = s;
    }

    /**
     * Opens a new, empty tab
     */
    public void newTab() {
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
        if (w == null)
            return;
        int i = tabbedPane.getSelectedIndex();
        windows.remove(w);
        tabbedPane.remove(i);
    }
}
