package sample.app.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

import java.util.ArrayList;

public class InitProductPropertiesTask extends Task<Void> {
    private final ObservableList<ProductProperty> productProperties;
    private final ArrayList<Product> databaseProducts;

    public InitProductPropertiesTask(ObservableList<ProductProperty> productProperties,
                                     ArrayList<Product> databaseProduct) {
        this.productProperties = productProperties;
        this.databaseProducts = databaseProduct;
    }

    @Override
    protected Void call() throws Exception {
        for (Product dbProduct : databaseProducts) {
            if (isCancelled()) {
                break;
            }
            Platform.runLater(() -> {
                productProperties.add(ProductConverter.toProperty(dbProduct));
            });
        }
        System.out.println("TASK DONE!");
        return null;
    }
}
