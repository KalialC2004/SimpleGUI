

import java.awt.Component;
import java.awt.GridLayout; // Added import
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A PageComponent that acts as a container for other components.
 * This is the key to creating complex layouts, like a dashboard.
 * It uses composition (a "HAS-A" relationship) to hold other PageComponents.
 */
public class Panel extends PageComponent {
    private JPanel panel;

    /**
     * Creates a new Panel with a specific layout manager.
     * This is for more advanced use.
     * Example: new Panel(new BorderLayout());
     *
     * @param layout The AWT LayoutManager to use (e.g., GridLayout, BorderLayout)
     */
    public Panel(LayoutManager layout) {
        this.panel = new JPanel(layout);
        this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Creates a new Panel with a simple grid layout.
     * This is the easiest way to create a dashboard grid.
     *
     * @param rows The number of rows in the grid
     * @param cols The number of columns in the grid
     * @param hgap The horizontal gap (in pixels) between components
     * @param vgap The vertical gap (in pixels) between components
     */
    public Panel(int rows, int cols, int hgap, int vgap) {
        // Internally create the GridLayout so students don't have to.
        LayoutManager layout = new GridLayout(rows, cols, hgap, vgap);
        this.panel = new JPanel(layout);
        this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Adds another PageComponent to this panel.
     *
     * @param component The component to add (e.g., a StatCard, a Chart)
     */
    public void add(PageComponent component) {
        this.panel.add(component.getAwtComponent());
    }

    @Override
    public Component getAwtComponent() {
        return this.panel;
    }
}

