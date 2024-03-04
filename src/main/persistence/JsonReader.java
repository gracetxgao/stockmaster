package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads profile from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Profile readProfile() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfile(jsonObject);
    }

    // EFFECTS: reads stock status from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StockList readStockList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStockList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses profile from JSON object and returns it
    private Profile parseProfile(JSONObject jsonObject) {
        BigDecimal funds = jsonObject.getBigDecimal("funds");
        BigDecimal profit = jsonObject.getBigDecimal("profit");
        BigDecimal netWorth = jsonObject.getBigDecimal("net worth");

        JSONObject jsonOwnedStocks = jsonObject.getJSONObject("owned stocks");
        HashMap<String, Integer> ownedStocks = parseOwnedStocks(jsonOwnedStocks);

        JSONArray jsonTransactionHistory = jsonObject.getJSONArray("transaction history");
        TransactionHistory transactionHistory = parseTransactionHistory(jsonTransactionHistory);
        Profile p = new Profile(funds, profit, netWorth, transactionHistory, ownedStocks);
        return p;
    }

    // EFFECTS: returns JSON transaction history as TransactionHistory
    private TransactionHistory parseTransactionHistory(JSONArray jsonTransactionHistory) {
        TransactionHistory transactionHistory = new TransactionHistory();
        for (int i = 0; i < jsonTransactionHistory.length(); i++) {
            JSONObject t = jsonTransactionHistory.getJSONObject(i);
            String stockName = t.getString("stock name");
            BigDecimal price = t.getBigDecimal("price");
            int amount = t.getInt("amount");
            Transaction transaction = new Transaction(stockName, price, amount);
            transactionHistory.addTransaction(transaction);
        }
        return transactionHistory;
    }

    // EFFECTS: returns JSON owned stocks array as hash map
    private HashMap<String, Integer> parseOwnedStocks(JSONObject jsonOwnedStocks) {
        HashMap<String, Integer> ownedStocks = new HashMap<String, Integer>();
        Set<String> companies = jsonOwnedStocks.keySet();
        List<String> companiesList = new ArrayList<>(companies);
        for (int i = 0; i < jsonOwnedStocks.length(); i++) {
            int amount = jsonOwnedStocks.getInt(companiesList.get(i));
            ownedStocks.put(companiesList.get(i), amount);
        }
        return ownedStocks;
    }

    // EFFECTS: parses stock list from JSON object and returns it
    private StockList parseStockList(JSONObject jsonObject) {
        JSONArray jsonStockList = jsonObject.getJSONArray("stocks");
        List<Stock> stocks = parseStocks(jsonStockList);
        StockList stockList = new StockList(stocks);
        return stockList;
    }

    // EFFECTS: returns JSON stock array as list of stocks
    private List<Stock> parseStocks(JSONArray jsonStockList) {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < jsonStockList.length(); i++) {
            JSONObject s = jsonStockList.getJSONObject(i);
            String company = s.getString("company");
            BigDecimal currPrice = s.getBigDecimal("price");
            JSONArray jsonPriceHistory = s.getJSONArray("price history");
            List<BigDecimal> priceHistory = new ArrayList<>();
            for (int j = 0; j < jsonPriceHistory.length(); j++) {
                BigDecimal price = jsonPriceHistory.getBigDecimal(j);
                priceHistory.add(price);
            }
            Stock stock = new Stock(company, currPrice, priceHistory);
            stocks.add(stock);
        }
        return stocks;
    }
}
