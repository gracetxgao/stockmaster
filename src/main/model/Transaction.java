package model;

import java.math.BigDecimal;

// represents a transaction given the stock exchanged and its price, negative for buying and positive for selling
public class Transaction {
    private Stock stock;
    private BigDecimal price;

    // EFFECTS: constructs a transaction with given stock and price
    public Transaction(Stock stock, BigDecimal price) {
        this.stock = stock;
        this.price = price;
    }
}
