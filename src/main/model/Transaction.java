package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;

// represents a transaction given the stock exchanged and price (amount positive for buying and negative for selling)
public class Transaction implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stock", stock);
        json.put("price", price);
        json.put("amount", amount);

        return json;
    }
}
