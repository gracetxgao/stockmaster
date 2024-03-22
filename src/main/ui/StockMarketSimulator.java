package ui;

import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class StockMarketSimulator extends JFrame {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = (int) SCREEN_SIZE.getWidth();
    public static final int HEIGHT = (int) SCREEN_SIZE.getHeight();
    private ProfilePanel pp;
    private StocksPanel sp;
    private StockMarket sm;
    private MenuPanel mp;

    public StockMarketSimulator() {
        super("stock market simulator");
        sm = new StockMarket();
        pp = new ProfilePanel(sm);
        sp = new StocksPanel(sm, sm.getStocks());
        mp = new MenuPanel(sm);

        add(sp);
        add(pp, BorderLayout.EAST);
        add(mp, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StockMarketSimulator();
    }
}
