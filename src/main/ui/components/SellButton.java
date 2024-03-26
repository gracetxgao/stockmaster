package ui.components;

import model.Stock;
import model.StockMarket;
import ui.StockPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellButton implements ActionListener {
    private StockMarket sm;
    private static JButton sell;
    private Stock stock;
    private StockPanel sp;

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
        System.out.println("tried to sell " + stock.getCompany());
    }
}
