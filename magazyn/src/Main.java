import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            WarehouseFrame frame = new WarehouseFrame();
            frame.setVisible(true);

        });
    }
}