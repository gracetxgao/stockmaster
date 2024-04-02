package ui;

import model.EventLog;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;

// represents a stock market simulator
public class StockMarketSimulator extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private ProfilePanel pp;
    private StocksPanel sp;
    private MenuPanel mp;
    private StockMarket sm;
    private FileWriter fw;

    // EFFECTS: constructs stock market simulator with profile, stocks, and menu panel
    public StockMarketSimulator() {
        super("stock market simulator");
        sm = new StockMarket();
        sp = new StocksPanel(sm, sm.getStocks(), this);
        pp = new ProfilePanel(sm, sm.getProfile());
        mp = new MenuPanel(sm);
        sm.setStocksPanel(sp);
        sm.setProfilePanel(pp);
        sm.setMenuPanel(mp);
        sm.setStockMarketSimulator(this);

        add(sp);
        add(pp, BorderLayout.EAST);
        add(mp, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog el = EventLog.getInstance();
                System.out.println("window closing");
                for (Event next : el) {
                    System.out.println(next.toString());
                }
                super.windowClosing(e);
            }
        });

        setSize(WIDTH, HEIGHT);

        setVisible(true);
    }

    // EFFECTS: creates popup error message
    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new StockMarketSimulator();
    }
}
