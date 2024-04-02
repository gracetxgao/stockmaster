package ui.components;

import ui.StockMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a button used to randomize the order of the transaction list
public class RandomizeButton implements ActionListener {
    private TransactionsPanel tp;
    private static JButton randomize;
    private StockMarket sm;

    // EFFECTS: constructs randomize button and adds to transaction panel
    public RandomizeButton(TransactionsPanel tp, StockMarket sm) {
        randomize = new JButton("randomize");
        randomize.addActionListener(this);
        tp.add(randomize);
        this.tp = tp;
        this.sm = sm;
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleRandomize();
    }
}
