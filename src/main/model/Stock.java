package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Stock {

    private String company;
    private BigDecimal price;
    private List<BigDecimal> priceHistory;

    public Stock(String company, BigDecimal price) {
        this.company = company;
        this.price = price;
        this.priceHistory = new ArrayList<BigDecimal>();
    }

    public void getNewPrice(double change) {
        BigDecimal newPrice;
        newPrice = this.price.multiply(BigDecimal.valueOf(change)).add(this.price);
        this.price = newPrice;
        this.priceHistory.add(newPrice);
    }

    public void viewHistory() {
        System.out.println(priceHistory);
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCompany() {
        return this.company;
    }
}
