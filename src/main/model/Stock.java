package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Stock {

    private String company;
    private int price;
    private List<Integer> priceHistory;

    public Stock(String company, int price) {
        this.company = company;
        this.price = price;
        this.priceHistory = new ArrayList<Integer>();
    }

    public void getNewPrice(int change) {
        int newPrice = this.price * (change / 100) + this.price;
        this.price = newPrice;
        this.priceHistory.add(newPrice);
    }

    public void viewHistory() {
        System.out.println(priceHistory);
    }

    public int getPrice() {
        return this.price;
    }

    public String getCompany() {
        return this.company;
    }
}
