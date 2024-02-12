package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactionHistory;

    public TransactionHistory() {
        this.transactionHistory = new ArrayList<Transaction>();
    }

    // MODIFIES: this
    // EFFECTS: adds transaction to transaction history
    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }

    public List<String> getTransactionHistory() {
        List<String> outputHistory = new ArrayList<String>();
        for (Transaction t : transactionHistory) {
            int amt = t.getAmount();
            String company = t.getStock().getCompany();
            BigDecimal price = t.getPrice();
            BigDecimal sellPrice = price.multiply(BigDecimal.valueOf(-1));
            if (price.compareTo(BigDecimal.valueOf(0)) == -1) {
                outputHistory.add("Bought " + amt + " shares of " + company + " for $" + sellPrice + " each");
            } else {
                outputHistory.add("Sold " + amt + " shares of " + company + " for $" + price + " each");
            }
        }

        return outputHistory;
    }

    public int getTransactionHistorySize() {
        return this.transactionHistory.size();
    }
}
