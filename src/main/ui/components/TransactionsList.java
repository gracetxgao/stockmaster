package ui.components;

import model.TransactionHistory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransactionsList {
    private JList<String> transactions;
    private DefaultListModel<String> data;
    private JScrollPane transactionsScrollPane;


    public TransactionsList(TransactionsPanel tp) {
        data = new DefaultListModel<>();
        transactions = new JList<>(data);
        transactionsScrollPane = new JScrollPane(transactions);
        tp.add(transactionsScrollPane, BorderLayout.CENTER);
    }

    public void setTransactionHistoryList(TransactionHistory transactionHistory) {
        data.removeAllElements();
        List<String> transactionHistoryStrings = transactionHistory.getTransactionHistory();
        for (int i = 0; i < transactionHistoryStrings.size(); i++) {
            data.addElement(transactionHistoryStrings.get(i));
        }
    }

    public void addTransaction(String transaction) {
        data.addElement(transaction);
    }

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
}
