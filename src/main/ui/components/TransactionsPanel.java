package ui.components;

import model.TransactionHistory;

import javax.swing.*;
import java.awt.*;

public class TransactionsPanel extends JPanel {
    private static JLabel transactionsLabel;
    private TransactionsList transactionsList;
    public static final int TRANSACTIONS_WIDTH = 300;
    public static final int TRANSACTIONS_HEIGHT = 300;
    private TransactionsFilter filter;

    public TransactionsPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(TRANSACTIONS_WIDTH, TRANSACTIONS_HEIGHT));
        transactionsLabel = new JLabel("transaction history");
        add(transactionsLabel);
        transactionsList = new TransactionsList(this);
        filter = new TransactionsFilter(this, transactionsList);
    }

    public void setTransactionHistoryList(TransactionHistory transactionHistory) {
        transactionsList.setTransactionHistoryList(transactionHistory);
    }

    public void addTransaction(String transaction) {
        transactionsList.addTransaction(transaction);
    }
}
