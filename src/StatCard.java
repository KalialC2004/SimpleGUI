
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout; // Import FlowLayout
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A PageComponent that displays a single large statistic (a "Key Performance
 * Indicator").
 * This is useful for dashboards.
 */
public class StatCard extends PageComponent {
    private JPanel cardPanel; // Renamed from 'panel' for clarity
    private JLabel valueLabel;
    private JLabel descriptionLabel;

    /**
     * Creates a new StatCard.
     * * @param description The small text label (e.g., "Total Students")
     * @param value       The large text value (e.g., "142")
     */
    public StatCard(String description, String value) {
        cardPanel = new JPanel(new BorderLayout(5, 5));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)));

        valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        cardPanel.add(valueLabel, BorderLayout.CENTER);
        cardPanel.add(descriptionLabel, BorderLayout.SOUTH);
        cardPanel.setPreferredSize(new Dimension(200, 120));
    }

    /**
     * Sets the main value text.
     * * @param value The new value to display
     */
    public void setValue(String value) {
        this.valueLabel.setText(value);
    }

    /**
     * Sets the small description text.
     * * @param description The new description to display
     */
    public void setDescription(String description) {
        this.descriptionLabel.setText(description);
    }

    @Override
    public Component getAwtComponent() {
        // --- FIX ---
        // Wrap the card in a FlowLayout panel.
        // This prevents the card from stretching when placed in a GridLayout.
        JPanel wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.add(cardPanel);
        return wrapperPanel;
    }
}

