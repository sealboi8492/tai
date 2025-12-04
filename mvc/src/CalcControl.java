import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcControl {
    private CalcModel model;
    private CalcView view;
    public CalcControl(CalcModel model, CalcView view) {
        this.model = model;
        this.view = view;

        // Podłączanie listenerów z Kontrolera do przycisków w Widoku
        this.view.addKwadratListener(new KwadratListener());

    }

    // Wewnętrzna klasa obsługująca kliknięcie 'Zwiększ'
    class KwadratListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int liczba = Integer.parseInt(view.inputField.getText());

            int wynik = model.kwadrat( liczba);


            view.setLicznikWartosc(wynik);
        }

    }}
