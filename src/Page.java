

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Page {
    private JPanel mainPanel;

    public Page() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        // Add 10px of padding around the entire page content
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void addComponent(PageComponent component) {
        addComponent(component, BorderLayout.CENTER);
    }

    public void addComponent(PageComponent component, String layoutPosition) {
        mainPanel.add(component.getAwtComponent(), layoutPosition);
    }

    public void setLayout(BorderLayout layout) {
        mainPanel.setLayout(layout);
    }

    public Component getAwtComponent() {
        return mainPanel;
    }
}

