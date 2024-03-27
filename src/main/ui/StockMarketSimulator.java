package ui;

import javax.swing.*;
import java.awt.*;

public class StockMarketSimulator extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private ProfilePanel pp;
    private StocksPanel sp;
    private MenuPanel mp;
    private StockMarket sm;

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

        setSize(WIDTH, HEIGHT);

        setVisible(true);
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s,"error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new StockMarketSimulator();
    }
}
