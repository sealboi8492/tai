import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreezeExample extends JFrame {
    private JLabel statusLabel;
    private JButton startButton;

    public FreezeExample() {
        super("Wersja Zawieszająca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());

        statusLabel = new JLabel("Czekam...");
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    statusLabel.setText("Pracuję... " + (i + 1) + "s");
                }
                statusLabel.setText("Zakończono blokadę.");
            }
        });

        add(startButton);
        add(statusLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FreezeExample::new);
    }
}