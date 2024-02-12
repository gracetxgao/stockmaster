package model;

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

    public List<Transaction> getTransactionHistory() {
        return this.transactionHistory;
    }
}
