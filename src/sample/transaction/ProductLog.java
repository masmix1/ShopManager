package sample.transaction;

import sample.transaction.single_product.SingleProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductLog implements Serializable {
    private final ArrayList<SingleProduct> products;

    public ProductLog() {
        products = new ArrayList<>();
    }

    public void put(SingleProduct product) {
        products.add(product);
    }

    public ArrayList<SingleProduct> get() {
        return products;
    }

    public boolean contains(SingleProduct product) {
        for (SingleProduct x : products) {
            if (x.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addQuantity(SingleProduct scannedProduct) {
        if (!this.contains(scannedProduct)) {
            throw new IllegalArgumentException("Product: " + scannedProduct.getName() + " is not in product log!");
        }

        retrieve(scannedProduct.getName()).addQuantity(scannedProduct.getQuantity());
    }

    private SingleProduct retrieve(String productName) {
        for (SingleProduct x : products) {
            if (x.getName().equals(productName)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Error! fixme!");
    }
}
