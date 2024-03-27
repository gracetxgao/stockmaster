package ui.components;

import ui.StockMarket;
import ui.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a button used to move onto a new day
public class NextButton implements ActionListener {
    private StockMarket sm;
    private static JButton next;

    // EFFECTS: constructs next button and adds to menu panel
    public NextButton(StockMarket sm, MenuPanel mp) {
        next = new JButton("next");
        next.addActionListener(this);
        mp.add(next);
        this.sm = sm;
    }

    public void actionPerformed(ActionEvent e) {
        sm.handleNextDay();
        System.out.println("next day clicked");
    }
}
