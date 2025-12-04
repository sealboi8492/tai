import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CalcModel model = new CalcModel();

            CalcView view = new CalcView();

            CalcControl controller = new CalcControl(model, view);

            view.setVisible(true);
        });
    }
}