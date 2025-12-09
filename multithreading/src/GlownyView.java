import javax.swing.*;

public class GlownyView extends JFrame {

    JTextField uname = new JTextField();
    JPasswordField pwd = new JPasswordField();
    JButton btn = new JButton("Zaloguj");
    JLabel status = new JLabel("Podaj nazwę użytkownika i hasło:");
    public GlownyView() {
        super("Logowanie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 150);
        uname.setColumns(10);
        pwd.setColumns(10);
        JPanel panel = new JPanel();
        panel.add(status);
        panel.add(uname);
        panel.add(pwd);
        panel.add(btn);
        this.add(panel);
    }

}