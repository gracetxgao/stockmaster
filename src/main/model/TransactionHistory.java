package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a list of transactions the user has made
public class TransactionHistory {
    private List<Transaction> transactionHistory;

    public TransactionHistory() {
        this.transactionHistory = new ArrayList<Transaction>();
    }

    // MODIFIES: this
    // EFFECTS: adds transaction to transaction history
    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
        EventLog.getInstance().logEvent(new Event("new transaction added with stock "
                + t.getStockName() + ", amount " + t.getAmount() + ", price " + t.getPrice()));
    }

    // EFFECTS: returns list of strings summarizing all transactions
    public List<String> getTransactionHistory() {
        List<String> outputHistory = new ArrayList<String>();
        for (Transaction t : transactionHistory) {
            int amt = t.getAmount();
            String company = t.getStockName();
            BigDecimal price = t.getPrice();
            if (amt < 0) {
                outputHistory.add("Sold " + Math.abs(amt) + " shares of " + company + " for $" + price + " each");
            } else {
                outputHistory.add("Bought " + amt + " shares of " + company + " for $" + price + " each");
            }
        }
        return outputHistory;
    }

    // EFFECTS: returns size of transaction history list
    public int getTransactionHistorySize() {
        return this.transactionHistory.size();
    }

    // EFFECTS: returns transactions in history as a JSON array
    public JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Transaction t : transactionHistory) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: filters transaction history for given input
    public TransactionHistory filter(String input) {
        TransactionHistory filteredTransactionHistory = new TransactionHistory();
        EventLog.getInstance().logEvent(new Event("filtering transaction history for: " + input));
        for (Transaction t : transactionHistory) {
            if (t.getStockName().toLowerCase().equals(input.toLowerCase())) {
                filteredTransactionHistory.addTransaction(t);
                EventLog.getInstance().logEvent(new Event("filter match: " + t.toJson()));
            }
        }
        return filteredTransactionHistory;
    }

    // MODIFIES: this
    // EFFECTS: shuffles transaction history
    public TransactionHistory randomize() {
        EventLog.getInstance().logEvent(new Event("shuffling transaction history"));
        List<Transaction> prev = new ArrayList<>();
        for (int i = 0; i < transactionHistory.size(); i++) {
            prev.add(transactionHistory.get(i));
        }
        Collections.shuffle(prev);
        TransactionHistory shuffled = new TransactionHistory();
        for (int i = 0; i < prev.size(); i++) {
            shuffled.addTransaction(prev.get(i));
        }
        return shuffled;
    }
}
