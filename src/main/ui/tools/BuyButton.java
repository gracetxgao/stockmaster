package ui.tools;

import model.Stock;
import model.StockMarket;
import ui.MenuPanel;
import ui.StockPanel;
import ui.StocksPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class BuyButton implements ActionListener {
    private StockMarket sm;
    private static JButton buy;
    private Stock stock;

    public BuyButton(StockMarket sm, StockPanel sp) {
        buy = new JButton("buy");
        buy.addActionListener(this);
        sp.add(buy);
        this.sm = sm;
        this.stock = sp.getStock();
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleBuyStock(this.stock);
        System.out.println("tried to buy " + this.stock.getCompany());
    }
}
