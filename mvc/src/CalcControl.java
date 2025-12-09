import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcControl {
    private CalcModel model;
    private CalcView view;
    public CalcControl(CalcModel model, CalcView view) {
        this.model = model;
        this.view = view;

        this.view.addKwadratListener(new KwadratListener());

    }
    class KwadratListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int liczba = Integer.parseInt(view.inputField.getText());

            int wynik = model.kwadrat( liczba);


            view.setLicznikWartosc(wynik);
        }

    }}

