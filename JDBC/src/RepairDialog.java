import javax.swing.*;

public class RepairDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnOK;
    private JButton btnCancel;
    private JTextField txtSprzet;
    private JTextField txtKoszt;
    private JLabel lblSprzet;
    private JLabel lblKoszt;

    private String inputSprzet;
        private double inputKoszt;
        private boolean isConfirmed = false;

        public RepairDialog() {
            setTitle("Dodaj nowe zgłoszenie");
            setModal(true);
            setSize(350, 180);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            setContentPane(contentPane);

            btnOK.addActionListener(e -> {
                String sprzet = txtSprzet.getText().trim();
                String kosztTxt = txtKoszt.getText().trim();

                if (sprzet.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Podaj nazwę sprzętu!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    kosztTxt = kosztTxt.replace(',', '.');
                    inputKoszt = Double.parseDouble(kosztTxt);
                    inputSprzet = sprzet;
                    isConfirmed = true;
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Błędna kwota! Podaj liczbę np. 150.50", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnCancel.addActionListener(e -> {
                isConfirmed = false;
                dispose();
            });
        }

        public String getInputSprzet() { return inputSprzet; }
        public double getInputKoszt() { return inputKoszt; }
        public boolean isConfirmed() { return isConfirmed; }
    }