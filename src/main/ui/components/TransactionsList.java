package ui.components;

import model.TransactionHistory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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

    // MODIFIES: this
    // EFFECTS: filters list for keywords
    public void filterTransactions(String text) {
        DefaultListModel<String> prev = new DefaultListModel<>();
        for (int i = 0; i < data.size(); i++) {
            prev.addElement(data.get(i));
        }
        data.removeAllElements();
        for (int i = 0; i < prev.size(); i++) {
            String curr = prev.get(i);
            if (curr.contains(text)) {
                data.addElement(curr);
            }
        }
    }

    public void randomize() {
        List<String> prev = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            prev.add(data.get(i));
        }
        Collections.shuffle(prev);
        data.removeAllElements();
        for (int i = 0; i < prev.size(); i++) {
            data.addElement(prev.get(i));
        }
    }
}
