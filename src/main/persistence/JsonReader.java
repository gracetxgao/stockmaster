package persistence;

import model.Profile;
import model.Stock;
import model.Transaction;
import model.TransactionHistory;
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
    public Profile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfile(jsonObject);
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
        BigDecimal funds = new BigDecimal(jsonObject.getInt("funds"));
        BigDecimal profit = new BigDecimal(jsonObject.getInt("profit"));
        BigDecimal netWorth = new BigDecimal(jsonObject.getInt("net worth"));

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
            BigDecimal price = new BigDecimal(t.getInt("price"));
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
}
