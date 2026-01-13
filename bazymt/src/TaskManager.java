import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton loadButton, addButton;
    private JLabel statusLabel;
    private JProgressBar progressBar;

    private final String DB_URL = "jdbc:mysql://localhost:3306/tasks";

    public TaskManager() {
        setTitle("Task Manager");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initDB();
        initUI();
    }

    private void initUI() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Description", "Done"}, 0);
        table = new JTable(tableModel);

        loadButton = new JButton("Wczytaj Zadania");
        addButton = new JButton("Dodaj Zadanie");

        statusLabel = new JLabel("Gotowy.");
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);

        loadButton.addActionListener(e -> {
            statusLabel.setText("Ładowanie danych... Proszę czekać.");
            loadButton.setEnabled(false);
            progressBar.setVisible(true);
            new LoadTasksWorker().execute();
        });

        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Tytuł zadania:");
            if (title != null && !title.isBlank()) {
                new AddTaskWorker(title, "Opis przykładowy").execute();
            }
        });

        JPanel top = new JPanel();
        top.add(loadButton);
        top.add(addButton);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(statusLabel, BorderLayout.CENTER);
        bottom.add(progressBar, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // --- DATABASE INIT ---
    private void initDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    is_done BOOLEAN NOT NULL
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- SWING WORKER: LOAD ---
    class LoadTasksWorker extends SwingWorker<List<Task>, Void> {

        @Override
        protected List<Task> doInBackground() throws Exception {
            Thread.sleep(4000); // sztuczne opóźnienie

            List<Task> tasks = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {

                while (rs.next()) {
                    tasks.add(new Task(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getBoolean("is_done")
                    ));
                }
            }
            return tasks;
        }

        @Override
        protected void done() {
            try {
                List<Task> tasks = get();
                tableModel.setRowCount(0);

                for (Task t : tasks) {
                    tableModel.addRow(new Object[]{
                            t.getId(),
                            t.getTitle(),
                            t.getDescription(),
                            t.isDone()
                    });
                }

                statusLabel.setText("Gotowe. Wczytano " + tasks.size() + " zadań.");
            } catch (Exception e) {
                statusLabel.setText("Błąd podczas wczytywania!");
            } finally {
                loadButton.setEnabled(true);
                progressBar.setVisible(false);
            }
        }
    }

    // --- SWING WORKER: ADD ---
    class AddTaskWorker extends SwingWorker<Boolean, Void> {

        private String title, desc;

        public AddTaskWorker(String title, String desc) {
            this.title = title;
            this.desc = desc;
        }

        @Override
        protected Boolean doInBackground() {
            String sql = "INSERT INTO tasks(title, description, is_done) VALUES (?, ?, false)";

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, title);
                ps.setString(2, desc);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                return false;
            }
        }

        @Override
        protected void done() {
            try {
                if (get()) {
                    statusLabel.setText("Zadanie dodane.");
                    new LoadTasksWorker().execute();
                } else {
                    statusLabel.setText("Błąd zapisu.");
                }
            } catch (Exception e) {
                statusLabel.setText("Błąd krytyczny.");
            }
        }
    }

    // --- MAIN ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManager().setVisible(true));
    }
}
