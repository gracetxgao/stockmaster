package ui.components;

import model.Stock;
import ui.StockMarket;
import ui.StockPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a button used to sell stocks
public class SellButton implements ActionListener {
    private StockMarket sm;
    private static JButton sell;
    private Stock stock;
    private StockPanel sp;

    // EFFECTS: constructs sell button and adds to stock panel
    public SellButton(StockMarket sm, StockPanel sp) {
        sell = new JButton("sell");
        sell.addActionListener(this);
        sp.add(sell);
        this.sm = sm;
        stock = sp.getStock();
        this.sp = sp;
    }

    public void actionPerformed(ActionEvent e) {
        stock = sp.getStock();
        sm.handleSellStock(stock);
    }
}
