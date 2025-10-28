module org.example.currencycalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.currencycalc to javafx.fxml;
    exports org.example.currencycalc;
}