package sample.transaction;

import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterProperty;
import sample.transaction.single_product.SingleProduct;
import sample.util.Util;

import java.util.ArrayList;

public final class TransactionBuilder {
    private ProductLog productLog;
    private double subtotalCost;
    private double totalCost = 0;
    private String date;
    private int productRow = 1;

    private ArrayList<Product> databaseProducts;

    public TransactionBuilder() {
        productLog = new ProductLog();
        databaseProducts = DatabaseHandler.getInstance().getProductArrayList();
    }

    public Transaction build() {
        calculateCost();
        getDate();

        Transaction transaction = new Transaction();
        transaction.setProductLog(productLog);
        transaction.setCost(totalCost);
        transaction.setDate("DummyDate");

        return transaction;
    }

    public void reset() {
        productLog = new ProductLog();
        subtotalCost = 0;
        totalCost = 0;
        date = "dummy";
        productRow = 0;
    }

    private void calculateCost() {
        // set subtotalcost, totalcost
    }

    private void getDate() {
    }

    // TODO unikatowe przedmioty - po zeskanowaniu nie powinien sie powtarzac na liscie -
    // - chyba ze dodac quantity do istniejacego produktu
    public CashRegisterProperty productScan() {
        Product dbProduct = databaseProducts.get(Util.random(0, databaseProducts.size()));
        String name = dbProduct.getName();
        double price = dbProduct.getPrice();
        int quantity = randomQuantity();

        SingleProduct scannedProduct = new SingleProduct(name, quantity, price);

        if (productLog.contains(scannedProduct)) {
            productLog.addQuantity(scannedProduct);
        }

        productLog.put(scannedProduct);
        updateTotalCost(quantity, price);

        return new CashRegisterProperty(productRow++, name, quantity, price);
    }

    private void updateTotalCost(int productQuantity, double productPrice) {
        totalCost += productPrice * productQuantity;
    }

    private int randomQuantity() {
        int quantity;
        int random = Util.random(0, 4);

        if (random <= 1) {
            quantity = 1;
        } else {
            random = Util.random(0, 10);

            if (random < 8) {
                quantity = Util.random(1, 3);
            } else {
                quantity = Util.random(1, 10);
            }
        }
        return quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }
}