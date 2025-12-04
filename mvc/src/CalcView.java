import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalcView extends JFrame {
    private JLabel liczLabel = new JLabel("Wartość liczby: 0");
    public JTextField inputField = new JTextField();
    private JButton liczButton = new JButton("Oblicz");

    public CalcView() {
        super("Licznik MVC");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 150);
        inputField.setColumns(4);
        JPanel panel = new JPanel();
        panel.add(liczLabel);
        panel.add(liczButton);
        panel.add(inputField);
        this.add(panel);
    }

    public void setLicznikWartosc(int nowaWartosc) {
        liczLabel.setText("Wartość liczby: " + nowaWartosc);
    }

    public void addKwadratListener(ActionListener listenForKwadratButton) {
        liczButton.addActionListener(listenForKwadratButton);
    }

}