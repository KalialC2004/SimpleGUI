

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane; // Required for the new static method

/**
 * A PageComponent for creating a data entry form.
 */
public class Form extends PageComponent {
    private JPanel panel;
    private JTextField[] textFields;
    private String[] fieldLabels;
    private FormSubmitHandler submitHandler;
    private JPanel buttonPanel; // Panel to hold the buttons

    /**
     * Creates a new Form.
     *
     * @param fieldLabels      An array of strings for the field labels (e.g., "Name",
     * "Email")
     * @param submitButtonText The text for the submit button (e.g., "Save", "Add")
     * @param handler          An object that implements the FormSubmitHandler
     * interface
     */
    public Form(String[] fieldLabels, String submitButtonText, FormSubmitHandler handler) {
        this.fieldLabels = fieldLabels;
        this.submitHandler = handler;
        this.textFields = new JTextField[fieldLabels.length];

        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel fieldsPanel = new JPanel(new GridLayout(fieldLabels.length, 2, 10, 10));
        for (int i = 0; i < fieldLabels.length; i++) {
            JLabel label = new JLabel(fieldLabels[i] + ":");
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            fieldsPanel.add(label);

            textFields[i] = new JTextField(20);
            textFields[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            fieldsPanel.add(textFields[i]);
        }
        panel.add(fieldsPanel, BorderLayout.CENTER);

        // --- Button Panel Setup ---
        buttonPanel = new JPanel(); // FlowLayout by default
        JButton submitButton = new JButton(submitButtonText);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (submitHandler != null) {
                    String[] values = new String[textFields.length];
                    for (int i = 0; i < textFields.length; i++) {
                        values[i] = textFields[i].getText();
                    }
                    submitHandler.onSubmit(values);
                    clearForm(); // Clear form on successful submit
                }
            }
        });
        buttonPanel.add(submitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an additional button to the form.
     * Example: a "Clear" button.
     *
     * @param buttonText The text for the button
     * @param action     A Runnable to execute when the button is clicked
     */
    public void addButton(String buttonText, final Runnable action) {
        JButton customButton = new JButton(buttonText);
        customButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        customButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        // Add to the same panel as the submit button
        buttonPanel.add(customButton);
    }

    /**
     * Clears all text fields in the form.
     */
    public void clearForm() {
        for (JTextField field : textFields) {
            field.setText("");
        }
    }

    @Override
    public Component getAwtComponent() {
        return panel;
    }

    /**
     * A simple helper to show a message dialog.
     * This abstracts away the JOptionPane dependency from the student's code.
     *
     * @param title   The title of the message box
     * @param message The message to display
     */
    public static void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

