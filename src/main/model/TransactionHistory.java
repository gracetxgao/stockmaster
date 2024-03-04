package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// represents a list of transactions the user has made
public class TransactionHistory implements Writable {
    private List<Transaction> transactionHistory;

    public TransactionHistory() {
        this.transactionHistory = new ArrayList<Transaction>();
    }

    // MODIFIES: this
    // EFFECTS: adds transaction to transaction history
    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }

    // EFFECTS: returns list of strings summarizing all transactions
    public List<String> getTransactionHistory() {
        List<String> outputHistory = new ArrayList<String>();
        for (Transaction t : transactionHistory) {
            int amt = t.getAmount();
            String company = t.getStock().getCompany();
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

    @Override
    public JSONObject toJson() {
        return null;
    }
}
