package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NavbarController {
    @FXML
    public Button backButton;
    @FXML
    public Button inventoryButton;
    @FXML
    public Button cashRegistersButton;
    @FXML
    public Button transactionsButton;

    private ArrayList<Button> buttons;

    @FXML
    private void initialize() {
        buttons = new ArrayList<>(3);
        buttons.add(inventoryButton);
        buttons.add(cashRegistersButton);
        buttons.add(transactionsButton);
    }

    public void disableButton(String location) {
        System.out.println("location received: " + location);
        for (Button x : buttons) {
            System.out.println(x.getText().replaceAll("\\s+",""));
            if (location.contains(x.getText().replaceAll("\\s+",""))) {
                System.out.println("BUTTON TEXT AFTER TRIM: " + x.getText().trim());
                System.out.println("Found button!\nDisabling: " + x.getText());
                x.setDisable(true);
                revertExcept(x);
            }
        }
    }

    private void revertExcept(Button button) {
        for (Button x : buttons) {
            if (!x.getText().equals(button.getText())) {
                x.setDisable(false);
            }
        }
    }

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainWindow.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleInventoryButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Inventory.fxml"));
            Stage stage = (Stage) inventoryButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleCashRegistersButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CashRegisters.fxml"));
            Stage stage = (Stage) cashRegistersButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
    }
}
