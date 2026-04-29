import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField inputField;
    private JLabel counterLabel;
    private JButton addButton;
    private JButton deleteButton;

    public Main() {
        setTitle("Menedżer zadań");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();

        JPanel topPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        addButton = new JButton("Dodaj");

        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        deleteButton = new JButton("Usuń zadanie");
        counterLabel = new JLabel("Zadań: 0");

        bottomPanel.add(deleteButton, BorderLayout.WEST);
        bottomPanel.add(counterLabel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTask());

        inputField.addActionListener(e -> addTask());

        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                listModel.remove(index);
                updateCounter();
            }
        });

        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = taskList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        listModel.remove(index);
                        updateCounter();
                    }
                }
            }
        });
    }

    private void addTask() {
        String text = inputField.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Zadanie nie może być puste!",
                    "Błąd",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        listModel.addElement(text);
        inputField.setText("");
        updateCounter();
    }

    private void updateCounter() {
        counterLabel.setText(listModel.getSize() + " Zadań");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}