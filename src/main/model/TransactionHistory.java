package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactionHistory;

    public TransactionHistory() {
        this.transactionHistory = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }
}
