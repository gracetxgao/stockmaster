package ui;

import model.Stock;
import model.StockList;
import model.StockMarket;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class StocksPanel extends JPanel {
    private static JLabel stocksLabel;
    private StockMarket sm;
    private StockList stocks;
    public static final int STOCKS_WIDTH = (int) (StockMarketSimulator.WIDTH * 0.6);
    public static final int STOCKS_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.8);

    public StocksPanel(StockMarket sm, StockList stocks) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        stocksLabel = new JLabel("stocks");
        add(stocksLabel);
        setPreferredSize(new Dimension(STOCKS_WIDTH, STOCKS_HEIGHT));
        this.sm = sm;
        this.stocks = stocks;
        addAllStocks();
    }

    public void addAllStocks() {
        for (int i = 0; i < stocks.getSize(); i++) {
            int x = i * STOCKS_WIDTH;
            int y = i * STOCKS_HEIGHT;
            add(new StockPanel(this, stocks.getStock(i), x, y));
        }
    }
}

