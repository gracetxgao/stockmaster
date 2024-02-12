package model;

public class Transaction {
    private Stock stock;
    private int price;

    public Transaction(Stock stock, int price) {
        this.stock = stock;
        this.price = price;
    }
}
