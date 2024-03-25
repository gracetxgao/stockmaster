package ui;

import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class StockMarketSimulator extends JFrame {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
//    public static final int WIDTH = (int) SCREEN_SIZE.getWidth();
//    public static final int HEIGHT = (int) SCREEN_SIZE.getHeight();
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private ProfilePanel pp;
    private StocksPanel sp;
    private MenuPanel mp;
    private StockMarket sm;

    public StockMarketSimulator() {
        super("stock market simulator");
        sm = new StockMarket();
        sp = new StocksPanel(sm, sm.getStocks());
        pp = new ProfilePanel(sm, sm.getProfile());
        mp = new MenuPanel(sm);
        sm.setStocksPanel(sp);
        sm.setProfilePanel(pp);
        sm.setMenuPanel(mp);

        add(sp);
        add(pp, BorderLayout.EAST);
        add(mp, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);

        setVisible(true);
    }

    public ProfilePanel getProfilePanel() {
        return pp;
    }

    public StocksPanel getStocksPanel() {
        return sp;
    }

    public MenuPanel getMenuPanel() {
        return mp;
    }

    public StockMarket getStockMarket() {
        return sm;
    }

    public static void main(String[] args) {
        new StockMarketSimulator();
    }
}
