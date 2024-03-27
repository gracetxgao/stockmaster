package ui.components;

import model.StockMarket;
import ui.MenuPanel;
import ui.StockPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewInfoButton implements ActionListener {
    private StockMarket sm;
    private static JButton view;
    private StockPanel sp;

    public ViewInfoButton(StockMarket sm, StockPanel sp) {
        view = new JButton("view");
        view.addActionListener(this);
        this.sm = sm;
        this.sp = sp;
        sp.add(view);
    }

    public void actionPerformed(ActionEvent e) {
        sp.showGraph();
        System.out.println("opened graph");
    }
}
