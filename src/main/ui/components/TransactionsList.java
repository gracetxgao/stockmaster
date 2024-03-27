package ui.components;

import ui.StockMarket;
import model.TransactionHistory;

import javax.swing.*;
import java.util.*;

public class TransactionsList {
    private StockMarket sm;
    private static JLabel label;
    private JList<String> transactions;
    private DefaultListModel<String> data;
    private JScrollPane transactionsScrollPane;

    public TransactionsList(StockMarket sm, TransactionsPanel tp, TransactionHistory transactionHistory) {
        data = new DefaultListModel<>();
        transactions = new JList<>(data);
        transactionsScrollPane = new JScrollPane(transactions);
        tp.add(transactionsScrollPane);
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
//        DefaultListModel<String> filteredData = new DefaultListModel<>();
//        for (int i = 0; i < data.size(); i++) {
//            String curr = data.get(i);
//            if (curr.contains(text)) {
//                filteredData.addElement(curr);
//            }
//        }
//        data = filteredData;
    }
}
