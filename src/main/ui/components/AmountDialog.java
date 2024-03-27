package ui.components;

import ui.StockMarket;

import javax.swing.*;

public class AmountDialog {
    private int amount;

    public AmountDialog(StockMarket sm) {
        amount = Integer.valueOf(JOptionPane.showInputDialog("enter amount: "));
    }

    public int getAmount() {
        return amount;
    }
}
