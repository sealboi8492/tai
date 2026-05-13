import javax.swing.*;
import java.awt.*;

public class WarehouseFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField categoryField;

    private JTable table;

    private ItemTableModel model;

    public WarehouseFrame() {

        setTitle("System magazynowy");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

        model = new ItemTableModel();

        // TESTOWE DANE
        model.addItem(new Item(1, "Laptop", 5, "Elektronika"));
        model.addItem(new Item(2, "Myszka", 20, "Akcesoria"));
        model.addItem(new Item(3, "Biurko", 3, "Meble"));

        // PANEL LEWY
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 1, 5, 5));

        idField = new JTextField();
        nameField = new JTextField();
        quantityField = new JTextField();
        categoryField = new JTextField();

        JButton addButton = new JButton("Dodaj");
        JButton deleteButton = new JButton("Usuń zaznaczone");

        formPanel.add(new JLabel("ID"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Nazwa"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Ilość"));
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Kategoria"));
        formPanel.add(categoryField);

        formPanel.add(addButton);
        formPanel.add(deleteButton);

        // TABELA
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // UKŁAD OKNA
        setLayout(new BorderLayout());

        add(formPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // OBSŁUGA DODAWANIA
        addButton.addActionListener(e -> addItem());

        // OBSŁUGA USUWANIA
        deleteButton.addActionListener(e -> removeSelectedItem());
    }

    private void addItem() {

        try {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();

            int quantity = Integer.parseInt(quantityField.getText());

            String category = categoryField.getText();

            Item item = new Item(
                    id,
                    name,
                    quantity,
                    category
            );

            model.addItem(item);

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pole ilość oraz ID muszą być liczbami!",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void removeSelectedItem() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {

            model.removeItem(selectedRow);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Zaznacz wiersz do usunięcia!"
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        categoryField.setText("");
    }
}