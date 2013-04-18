package se362.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 * A link view for the fileWindow
 * 
 * @author Kevin Mulligan, kam9115@rit.edu
 * 
 */
@SuppressWarnings("serial")
public class LinkView extends JFrame {
    private FileWindow window;
    private JTextArea textArea;

    public LinkView(FileWindow w) {
        this.window = w;
        setBounds(0, 0, 400, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
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

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        String text = w.getAllText();
        System.out.println(text);
        Pattern p = Pattern.compile("href=\"([A-Za-z.:\\/-_()%]*)\"");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(text.substring(m.start(), m.end()));
            textArea.setText(textArea.getText()
                    + text.substring(m.start() + 6, m.end() - 1) + "\n");
        }

        JMenuBar menu = new JMenuBar();
        JMenuItem refresh = new JMenuItem("Refresh", new ImageIcon(
                GUI.class.getResource("/refresh.png")));
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refresh();
            }
        });
        menu.add(refresh);

        setJMenuBar(menu);

        setVisible(true);
    }

    /**
     * Refresh the visible links
     */
    public void refresh() {
        textArea.setText("");
        String text = window.getAllText();
        Pattern p = Pattern.compile("href=\"([A-Za-z.:\\/-_()%]*)\"");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(text.substring(m.start(), m.end()));
            textArea.setText(textArea.getText()
                    + text.substring(m.start() + 6, m.end() - 1) + "\n");
        }
    }

    /**
     * Close the window
     */
    public void close() {
        this.dispose();
        window.clearLinkView();
    }

}
