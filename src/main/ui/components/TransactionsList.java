package ui.components;

import model.Stock;
import model.StockMarket;
import model.Transaction;
import model.TransactionHistory;
import ui.ProfilePanel;
import ui.StockPanel;

import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;

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
        for (int i = 0; i < data.size(); i++) {
            if (!data.contains(text)) {
                data.removeElement(i);
            }
        }
    }
}
