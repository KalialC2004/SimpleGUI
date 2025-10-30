

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Text extends PageComponent {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Text(String initialContent) {
        textArea = new JTextArea(initialContent);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        scrollPane = new JScrollPane(textArea);
        // Add padding
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void setText(String newText) {
        textArea.setText(newText);
    }

    public void appendText(String additionalText) {
        textArea.append(additionalText);
    }

    @Override
    public Component getAwtComponent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}

