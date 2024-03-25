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

public class SellButton implements ActionListener {
    private StockMarket sm;
    private static JButton sell;
    private Stock stock;

    public SellButton(StockMarket sm, StockPanel sp) {
        sell = new JButton("sell");
        sell.addActionListener(this);
        sp.add(sell);
        this.sm = sm;
        this.stock = sp.getStock();
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleSellStock(this.stock);
        System.out.println("tried to sell " + this.stock.getCompany());
    }
}
