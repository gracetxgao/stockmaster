package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockList implements Writable {
    private List<Stock> stocks;

    // EFFECTS: constructs empty list of stocks in market
    public StockList() {
        this.stocks = new ArrayList<Stock>();
    }

    // EFFECTS: constructs list of stocks in market, for use when reading from file
    public StockList(List<Stock> stocks) {
        this.stocks = stocks;
    }

    // MODIFIES: this
    // EFFECTS: adds stock to market
    public void addStock(Stock s) {
        stocks.add(s);
    }

    // EFFECTS: returns stock at given index
    public Stock getStock(int i) {
        return stocks.get(i);
    }

    // EFFECTS: returns size of stock list
    public int getSize() {
        return stocks.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stocks", stocksToJson());

        return json;
    }

    // EFFECTS: returns stock list as a JSON array
    public JSONArray stocksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Stock s : stocks) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
