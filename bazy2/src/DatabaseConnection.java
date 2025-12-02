import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseConnection extends JFrame {

     JTable table;
     DefaultTableModel model;

     JTextField tytulField;
     JTextField autorField;
     JTextField rokField;

     Connection conn;

    public DatabaseConnection() {
        setTitle("Baza książek - Swing + JDBC");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        connectToDB();
        initComponents();
        loadTableData();
    }

    private void connectToDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/biblioteka";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd połączenia z bazą!");
        }
    }

    private void initComponents() {
        model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Autor", "Rok"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // Pola tekstowe
        tytulField = new JTextField(10);
        autorField = new JTextField(10);
        rokField = new JTextField(5);

        // Przyciski
        JButton addBtn = new JButton("Dodaj");
        JButton deleteBtn = new JButton("Usuń");
        JButton updateBtn = new JButton("Aktualizuj");

        // Panel wejściowy
        JPanel panel = new JPanel();
        panel.add(new JLabel("Tytuł:"));
        panel.add(tytulField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Rok:"));
        panel.add(rokField);
        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(updateBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Zdarzenie: DODAWANIE
        addBtn.addActionListener(e -> addBook());

        // Zdarzenie: USUWANIE
        deleteBtn.addActionListener(e -> deleteBook());

        // Zdarzenie: AKTUALIZACJA
        updateBtn.addActionListener(e -> updateBook());

        // Kliknięcie w tabelę → wypełnienie pól tekstowych
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    tytulField.setText(model.getValueAt(row, 1).toString());
                    autorField.setText(model.getValueAt(row, 2).toString());
                    rokField.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void loadTableData() {
        try {
            model.setRowCount(0); // Wyczyść
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ksiazki");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addBook() {
        String tytul = tytulField.getText();
        String autor = autorField.getText();
        String rok = rokField.getText();

        try {
            String sql = "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tytul);
            ps.setString(2, autor);
            ps.setInt(3, Integer.parseInt(rok));
            ps.executeUpdate();

            loadTableData(); // odświeżenie
            clearFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas dodawania!");
        }
    }

    // -----------------------
    // USUWANIE
    // -----------------------
    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Wybierz książkę do usunięcia!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        try {
            String sql = "DELETE FROM ksiazki WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            loadTableData();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania!");
        }
    }

    // -----------------------
    // AKTUALIZACJA
    // -----------------------
    private void updateBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Wybierz książkę do aktualizacji!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        String tytul = tytulField.getText();
        String autor = autorField.getText();
        String rok = rokField.getText();

        try {
            String sql = "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tytul);
            ps.setString(2, autor);
            ps.setInt(3, Integer.parseInt(rok));
            ps.setInt(4, id);
            ps.executeUpdate();

            loadTableData();
            clearFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas aktualizacji!");
        }
    }

    // Czyszczenie pól
    private void clearFields() {
        tytulField.setText("");
        autorField.setText("");
        rokField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatabaseConnection().setVisible(true));
    }
}
