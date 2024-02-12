package model;

import java.math.BigDecimal;

public class Transaction {
    private Stock stock;
    private BigDecimal price;

    public Transaction(Stock stock, BigDecimal price) {
        this.stock = stock;
        this.price = price;
    }
}
