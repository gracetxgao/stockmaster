package model;

import java.math.BigDecimal;

// represents a transaction given the stock exchanged and its price, negative for buying and positive for selling
public class Transaction {
    private Stock stock;
    private BigDecimal price;
    private int amount;

    // EFFECTS: constructs a transaction with given stock, price, and amount of shares bought
    public Transaction(Stock stock, BigDecimal price, int amount) {
        this.stock = stock;
        this.price = price;
        this.amount = amount;
    }

    public Stock getStock() {
        return this.stock;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }
}
