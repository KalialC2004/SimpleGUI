

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.Hashtable;

public class App {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private Hashtable pages;

    public App(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        tabbedPane = new JTabbedPane();
        pages = new Hashtable();

        try {
            // This line applies a modern look and feel to the entire application
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // If Nimbus isn't available, it will just use the default.
            System.err.println("Could not set Nimbus look and feel.");
        }

        frame.add(tabbedPane);
    }

    public Page addPage(String title) {
        Page page = new Page();
        tabbedPane.addTab(title, page.getAwtComponent());
        pages.put(title, page);
        return page;
    }

    public Page getPage(String title) {
        return (Page) pages.get(title);
    }

    public void show() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}

