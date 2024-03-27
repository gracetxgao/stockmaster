package ui.components;

import ui.StockMarket;
import ui.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a button used to reload market and profile status from file
public class ReloadButton implements ActionListener {
    private StockMarket sm;
    private static JButton reload;

    // EFFECTS: constructs reload button and adds to menu panel
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
