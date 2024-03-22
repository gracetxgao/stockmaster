package model;

import persistence.JsonReader;
import persistence.JsonWriter;

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

    public StockList getStocks() {
        return stocks;
    }
}
