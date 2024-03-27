package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;

// represents a transaction given the stock exchanged and price (amount positive for buying and negative for selling)
public class Transaction implements Writable {
    private String stockName;
    private BigDecimal price;
    private int amount;

    // EFFECTS: constructs a transaction with given stock, price, and amount of shares bought
    public Transaction(String stockName, BigDecimal price, int amount) {
        this.stockName = stockName;
        this.price = price;
        this.amount = amount;
    }

    public String getStockName() {
        return this.stockName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setStockName(String s) {
        this.stockName = s;
    }

    public void setPrice(BigDecimal p) {
        this.price = p;
    }

    public void setAmount(Integer a) {
        this.amount = a;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stock name", stockName);
        json.put("price", price);
        json.put("amount", amount);

        return json;
    }
}
