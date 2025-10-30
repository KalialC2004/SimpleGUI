

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Table extends PageComponent {
    private JTable table;
    private DefaultTableModel model;
    private Vector originalObjects; // To store the original Rowable objects

    public Table(String[] columnNames, Rowable[] initialData) {
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        originalObjects = new Vector();

        // --- Aesthetic Improvements ---
        table.setRowHeight(24); // Make rows taller
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14)); // Make header bold
        // ---

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if (initialData != null) {
            for (int i = 0; i < initialData.length; i++) {
                addRow(initialData[i]);
            }
        }
    }

    public void addRow(Rowable rowObject) {
        originalObjects.add(rowObject);
        model.addRow(rowObject.getRowData());
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public void addRowSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    public int getRowCount() {
        return model.getRowCount();
    }

    /**
     * Returns the original Rowable objects that were added to the table.
     * This is much more useful than getting raw data.
     */
    public Object[] getTableData() {
        return originalObjects.toArray();
    }

    @Override
    public Component getAwtComponent() {
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}

