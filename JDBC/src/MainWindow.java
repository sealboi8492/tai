import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MainWindow extends JFrame {
    private DefaultTableModel tableModel;
    private JButton btnDodaj;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel contentPane;

    public MainWindow() {
        setTitle("Serwis - System Rejestracji Usterek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        String[] kolumny = {"ID", "Sprzęt", "Koszt (PLN)"};
        tableModel = new DefaultTableModel(kolumny, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; table.setModel(tableModel);
        setContentPane(contentPane);
        refreshTable();

        btnDodaj.addActionListener(e -> openDialog());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        String query = "SELECT id, sprzet, koszt_naprawy FROM zgloszenia ORDER BY id";

        try (Connection conn = DbConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("sprzet"),
                        rs.getDouble("koszt_naprawy")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Błąd odczytu: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDialog() {
        RepairDialog dialog = new RepairDialog();
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            insertData(dialog.getInputSprzet(), dialog.getInputKoszt());
            refreshTable(); }
    }

    private void insertData(String sprzet, double koszt) {
        String sql = "INSERT INTO zgloszenia(sprzet, koszt_naprawy) VALUES(?, ?)";

        try (Connection conn = DbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sprzet);
            pstmt.setDouble(2, koszt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Błąd zapisu: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { e.printStackTrace(); }
            new MainWindow().setVisible(true);
        });
    }
}