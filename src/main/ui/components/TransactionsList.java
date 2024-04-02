package ui.components;

import model.TransactionHistory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// represents a visual transaction history list
public class TransactionsList {
    private JList<String> transactions;
    private DefaultListModel<String> data;
    private JScrollPane transactionsScrollPane;

    // EFFECTS: constructs list of transactions in scrollable pane and adds to transaction panel
    public TransactionsList(TransactionsPanel tp) {
        data = new DefaultListModel<>();
        transactions = new JList<>(data);
        transactionsScrollPane = new JScrollPane(transactions);
        tp.add(transactionsScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: clears list and adds all new elements
    public void setTransactionHistoryList(TransactionHistory transactionHistory) {
        data.removeAllElements();
        List<String> transactionHistoryStrings = transactionHistory.getTransactionHistory();
        for (int i = 0; i < transactionHistoryStrings.size(); i++) {
            data.addElement(transactionHistoryStrings.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new element
    public void addTransaction(String transaction) {
        data.addElement(transaction);
    }

}
