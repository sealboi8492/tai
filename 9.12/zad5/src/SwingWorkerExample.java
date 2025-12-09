import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class SwingWorkerExample extends JFrame {
    private JLabel statusLabel;
    private JButton startButton;

    public SwingWorkerExample() {
        super("Wersja niezawieszająca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());

        statusLabel = new JLabel("Czekam...");
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingWorker<Void, String> worker = new SwingWorker<>() {

                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            publish("Pracuję... " + (i + 1) + "s");
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<String> chunks) {

                        statusLabel.setText(chunks.get(chunks.size() - 1));
                    }

                    @Override
                    protected void done() {
                        statusLabel.setText("Zakończono pomyślnie!");
                    }
                };

                worker.execute();
            }
        });

        add(startButton);
        add(statusLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingWorkerExample::new);
    }
}