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

public class ReloadButton implements ActionListener {
    private StockMarket sm;
    private static JButton reload;

    public ReloadButton(StockMarket sm, MenuPanel mp) {
        reload = new JButton("reload");
        reload.addActionListener(this);
        mp.add(reload);
        this.sm = sm;
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleReload();
        System.out.println("reloaded status");
    }
}
