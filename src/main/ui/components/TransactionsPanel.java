package ui.components;

import model.TransactionHistory;

import javax.swing.*;
import java.awt.*;

// represents panel used to hold transaction history and associated elements
public class TransactionsPanel extends JPanel {
    private static JLabel transactionsLabel;
    private TransactionsList transactionsList;
    public static final int TRANSACTIONS_WIDTH = 275;
    public static final int TRANSACTIONS_HEIGHT = 250;
    private TransactionsFilter filter;
    private RandomizeButton randomize;

    // constructs transactions panel with list, filter, and randomize and adds to transaction panel
    public TransactionsPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(TRANSACTIONS_WIDTH, TRANSACTIONS_HEIGHT));
        transactionsLabel = new JLabel("transaction history");
        add(transactionsLabel);
        transactionsList = new TransactionsList(this);
        filter = new TransactionsFilter(this, transactionsList);
        randomize = new RandomizeButton(this);
    }

    // MODIFIES: this
    // EFFECTS: sets transaction history list
    public void setTransactionHistoryList(TransactionHistory transactionHistory) {
        transactionsList.setTransactionHistoryList(transactionHistory);
    }

    // MODIFIES: this
    // EFFECTS: puts transaction history list in random order
    public void randomize() {
        transactionsList.randomize();
    }

    // MODIFIES: this
    // EFFECTS: adds new transaction to list
    public void addTransaction(String transaction) {
        transactionsList.addTransaction(transaction);
    }
}
