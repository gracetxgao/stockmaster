package ui.components;

import ui.StockMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a text box used to filter items in the transaction history list
public class TransactionsFilter implements ActionListener {
    private static JTextField text;
    private static JLabel filterLabel;
    private String target;
    private StockMarket sm;

    // EFFECTS: constructs filter text box for transaction list and adds to transaction panel
    public TransactionsFilter(TransactionsPanel tp, StockMarket sm) {
        filterLabel = new JLabel("filter: ");
        tp.add(filterLabel);
        target = null;
        text = new JTextField(16);
        text.addActionListener(this);
        tp.add(text);
        this.sm = sm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        target = text.getText();
        sm.handleFilterTransactionHistory(target);
    }
}
