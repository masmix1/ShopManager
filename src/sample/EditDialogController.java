package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.product.ProductProperty;

public class EditDialogController {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField priceTextField;

    private Stage dialogStage;
    private ProductProperty product;

    DatabaseHandler db = DatabaseHandler.getInstance();

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleOkButton(ActionEvent actionEvent) {
        // if price text field doesn't contain number, set it's text to original price
        if (!priceTextField.getText().matches("[0-9.]+")) {
            priceTextField.setText(Double.toString(product.getPrice()));
            return;
        }

        // if changes to input fields have been made - save them, otherwise close the dialog
        if (!product.getName().equals(nameTextField.getText())
                || product.getPrice() != Double.parseDouble(priceTextField.getText())) {

            product.setName(nameTextField.getText());
            product.setPrice(Double.parseDouble(priceTextField.getText()));
            db.update(product);
        }
        dialogStage.close();
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        dialogStage.close();
    }

    /**
     * Sets fields info to product about to be edited.
     * @param product
     */
    public void setProduct(ProductProperty product) {
        this.product = product;

        nameTextField.setText(product.getName());
        priceTextField.setText(Double.toString(product.getPrice()));
    }
}
