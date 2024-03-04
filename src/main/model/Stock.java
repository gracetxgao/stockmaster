package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// represents a stock that has a company name, current price, and price history
public class Stock implements Writable {

    private String company;
    private BigDecimal price;
    private List<BigDecimal> priceHistory;

    // EFFECTS: constructs stock given company, current price, and with an empty list to represent price history
    public Stock(String company, BigDecimal price) {
        this.company = company;
        this.price = price;
        this.priceHistory = new ArrayList<BigDecimal>();
        this.priceHistory.add(price);
    }

    // MODIFIES: this
    // EFFECTS: changes stock price by given percentage and adds previous price to price history
    public void getNewPrice(double change) {
        BigDecimal newPrice;
        newPrice = this.price.multiply(BigDecimal.valueOf(change)).add(this.price);
        BigDecimal roundedNewPrice;
        roundedNewPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        this.price = roundedNewPrice;
        this.priceHistory.add(roundedNewPrice);
    }

    // EFFECTS: prints price history
    public List<BigDecimal> viewHistory() {
        return this.priceHistory;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCompany() {
        return this.company;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
