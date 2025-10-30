

import java.awt.Component;

/**
 * An abstract base class for all components that can be added to a Page.
 */
public abstract class PageComponent {
    /**
     * Gets the underlying AWT/Swing component to be added to the layout.
     * @return The AWT Component.
     */
    public abstract Component getAwtComponent();
}

