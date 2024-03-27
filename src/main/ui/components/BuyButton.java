package ui.components;

import model.Stock;
import ui.StockMarket;
import ui.StockPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a button used to buy stocks
public class BuyButton implements ActionListener {
    private StockMarket sm;
    private static JButton buy;
    private Stock stock;
    private StockPanel sp;

    // EFFECTS: constructs buy button and adds to stock panel
    public BuyButton(StockMarket sm, StockPanel sp) {
        buy = new JButton("buy");
        buy.addActionListener(this);
        sp.add(buy);
        this.sm = sm;
        stock = sp.getStock();
        this.sp = sp;
    }

    public void actionPerformed(ActionEvent e) {
        stock = sp.getStock();
        sm.handleBuyStock(stock);
        System.out.println("tried to buy " + stock.getCompany());
    }
}
