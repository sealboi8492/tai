import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            uczenModel model = new uczenModel();

            GlownyView view = new GlownyView();

            GlownyControl controller = new GlownyControl(model, view);


            view.setVisible(true);
        });
    }
}