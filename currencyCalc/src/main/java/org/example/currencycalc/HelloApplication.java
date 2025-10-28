package org.example.currencycalc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        ObservableList<String> waluty = FXCollections.observableArrayList("USD", "PLN", "EUR");

        GridPane grid = new GridPane();
        Label wynik = new Label("Tutaj pojawi się wynik.");
        Label kwotaSrc = new Label("Kwota wejściowa:");
        Label kwotaDst = new Label("Kwota wyjściowa:");

        TextField kwota = new TextField();

        ComboBox walutaSrc = new ComboBox(waluty);
        ComboBox walutaDst = new ComboBox<>(waluty);


        Button btn = new Button("Przelicz");
        btn.setOnAction(e -> wynik.setText(kwota.getText() + " " + walutaDst.getValue()));

        javafx.geometry.Insets ins = new javafx.geometry.Insets(20, 20, 20, 20);


        grid.add(kwotaSrc, 0, 0);
        grid.add(kwota, 1, 0);
        grid.add(walutaSrc, 2, 0);

        grid.add(kwotaDst, 0, 1);
        grid.add(wynik, 1, 1);
        grid.add(walutaDst, 2, 1);

        grid.add(btn, 0, 2, 3, 1);

        grid.setPadding(ins);

        grid.setVgap(30);
        grid.setHgap(30);

        btn.setMaxWidth(Double.MAX_VALUE);

        primaryStage.setScene(new Scene(grid, 420, 240));
        primaryStage.show();

    }
}
