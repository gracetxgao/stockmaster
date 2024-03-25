package model;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.MenuPanel;
import ui.ProfilePanel;
import ui.StockPanel;
import ui.StocksPanel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class StockMarket {
    private static final String JSON_STORE_PROFILE = "./data/profile.json";
    private static final String JSON_STORE_STOCKS = "./data/stocks.json";
    private Profile profile;
    private StockList stocks;
    private JsonWriter jsonWriterProfile;
    private JsonWriter jsonWriterStocks;
    private JsonReader jsonReaderProfile;
    private JsonReader jsonReaderStocks;
    private ProfilePanel pp;
    private StocksPanel sp;
    private MenuPanel mp;

    // MODIFIES: this
    // EFFECTS: initializes all fields
    public StockMarket() {
        Stock apple = new Stock("AAPL", BigDecimal.valueOf(150.00));
        Stock google = new Stock("GOOG", BigDecimal.valueOf(125.00));
        Stock nvidia = new Stock("NVDA", BigDecimal.valueOf(720.00));
        Stock amazon = new Stock("AMZN", BigDecimal.valueOf(175.00));
        Stock rivian = new Stock("RIVN", BigDecimal.valueOf(20.00));
        Stock tesla = new Stock("TSLA", BigDecimal.valueOf(30.00));
        stocks = new StockList();
        stocks.addStock(apple);
        stocks.addStock(google);
        stocks.addStock(nvidia);
        stocks.addStock(amazon);
        stocks.addStock(rivian);
        stocks.addStock(tesla);
        profile = new Profile(stocks);
        jsonWriterProfile = new JsonWriter(JSON_STORE_PROFILE);
        jsonWriterStocks = new JsonWriter(JSON_STORE_STOCKS);
        jsonReaderProfile = new JsonReader(JSON_STORE_PROFILE);
        jsonReaderStocks = new JsonReader(JSON_STORE_STOCKS);
    }

    public void setStocksPanel(StocksPanel sp) {
        this.sp = sp;
    }

    public void setProfilePanel(ProfilePanel pp) {
        this.pp = pp;
    }

    public void setMenuPanel(MenuPanel mp) {
        this.mp = mp;
    }

    public StockList getStocks() {
        return stocks;
    }

    public Profile getProfile() {
        return profile;
    }

    // MODIFIES: this
    // EFFECTS: allow user to purchase X amounts of the stock
    public void handleBuyStock(Stock s) {
        int amount = sp.chooseAmount();
        profile.buyStock(s, amount);
        pp.setFundsLabel(profile.getFunds());
        pp.setProfitLabel(profile.getProfit());
    }

    // MODIFIES: this
    // EFFECTS: allow user to purchase X amounts of the stock
    public void handleSellStock(Stock s) {
        int amount = (-1 * sp.chooseAmount());
        profile.sellStock(s, amount);
        pp.setFundsLabel(profile.getFunds());
        pp.setProfitLabel(profile.getProfit());
    }

    // MODIFIES: this
    // EFFECTS: generates new prices for each stock and updates net worth accordingly
    public void handleNextDay() {
        for (int i = 0; i < stocks.getSize(); i++) {
            Stock s = stocks.getStock(i);
            BigDecimal prevPrice = s.getPrice();
            s.getNewPrice(getPercentageChange());
            BigDecimal newPrice = s.getPrice();
            sp.getStockPanelList().get(i).setStockPriceLabel(newPrice);
            BigDecimal change = newPrice.subtract(prevPrice);
            int amountOwned = profile.getOwnedStocks().get(s.getCompany());
            for (int j = 0; j < amountOwned; j++) {
                profile.changeNetWorth(change);
                pp.setNetWorthLabel(profile.getNetWorth());
            }
        }
    }

    // EFFECTS: generates value for percentage change (as decimal, eg 0.01)
    //          if value is odd, turn negative (arbitrary choice so that value does not only grow)
    private double getPercentageChange() {
        double change = Math.random() / 10;
        if (Math.round(change * 1000) % 2 == 1) {
            change = -change;
        }
        return change;
    }

    // EFFECTS: saves profile and market status to file
    public void handleSave() {
        try {
            jsonWriterProfile.open();
            jsonWriterProfile.write(profile);
            jsonWriterProfile.close();
            System.out.println("Saved profile status to " + JSON_STORE_PROFILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_PROFILE);
        }
        try {
            jsonWriterStocks.open();
            jsonWriterStocks.write(stocks);
            jsonWriterStocks.close();
            System.out.println("Saved market status to " + JSON_STORE_STOCKS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_STOCKS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads profile and market status from file
    public void handleReload() {
        try {
            profile = jsonReaderProfile.readProfile();
            pp.setNetWorthLabel(profile.getNetWorth());
            pp.setFundsLabel(profile.getFunds());
            pp.setProfitLabel(profile.getProfit());
            System.out.println("Loaded profile status from " + JSON_STORE_PROFILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PROFILE);
        }
        try {
            stocks = jsonReaderStocks.readStockList();
            for (int i = 0; i < stocks.getSize(); i++) {
                sp.getStockPanelList().get(i).setStockPriceLabel(stocks.getStock(i).getPrice());
            }
            System.out.println("Loaded market status from " + JSON_STORE_STOCKS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_STOCKS);
        }
    }
}
