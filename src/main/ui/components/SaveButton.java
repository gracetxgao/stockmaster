package ui.components;

import model.StockMarket;
import ui.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton implements ActionListener {
    private StockMarket sm;
    private static JButton save;

    public SaveButton(StockMarket sm, MenuPanel mp) {
        save = new JButton("save");
        save.addActionListener(this);
        mp.add(save);
        this.sm = sm;
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleSave();
        System.out.println("saved status");
    }
}
