import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlownyControl {
    GlownyView view;
    uczenModel model;
    String pwd;
    String uname;
    boolean valid;

    public GlownyControl(uczenModel model, GlownyView view){
        view.btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){

            SwingWorker<Void, String> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    pwd = view.pwd.getText();
                    uname = view.uname.getText();

                    view.btn.setEnabled(false);
                    view.status.setText("Trwa weryfikacja...");

                    valid = model.walidujLogowanie(uname, pwd);
                    return null;
                }
                @Override
                protected void done(){
                    view.btn.setEnabled(true);
                    if(valid){
                        view.status.setText("Zalogowano pomyślnie!");
                    }
                    else{
                        view.status.setText("Zalogowanie nie powiodło się.");
                    }

                }
            };
        worker.execute();
        }
    });
    }
    }