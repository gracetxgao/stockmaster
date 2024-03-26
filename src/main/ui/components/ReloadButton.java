package ui.components;

import model.StockMarket;
import ui.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadButton implements ActionListener {
    private StockMarket sm;
    private static JButton reload;

    public ReloadButton(StockMarket sm, MenuPanel mp) {
        reload = new JButton("load");
        reload.addActionListener(this);
        mp.add(reload);
        this.sm = sm;
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleReload();
        System.out.println("loaded status");
    }
}
