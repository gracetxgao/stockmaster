package ui;

import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// represents stocks panel that holds all individual stock panels
public class StocksPanel extends JPanel {
    private StockMarket sm;
    private StockList stocks;
    public static final int STOCKS_WIDTH = (int) (StockMarketSimulator.WIDTH * 600);
    public static final int STOCKS_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 700);
    private List<StockPanel> spList;
    private StockMarketSimulator sms;

    // EFFECTS: constructs stocks panel with each stock panel inside
    public StocksPanel(StockMarket sm, StockList stocks, StockMarketSimulator sms) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(STOCKS_WIDTH, STOCKS_HEIGHT));
        this.sm = sm;
        this.stocks = stocks;
        spList = new ArrayList<>();
        makeStockPanels();
        this.sms = sms;
    }

    // MODIFIES: this
    // EFFECTS: sets up stock panel for each stock
    public void updateStocks(StockList sl) {
        for (int i = 0; i < stocks.getSize(); i++) {
            spList.get(i).setStock(sl.getStock(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: creates stock panel for each stock
    public void makeStockPanels() {
        for (int i = 0; i < stocks.getSize(); i++) {
            int x = (i % 3) * 200;
            int y = i * 200;
            StockPanel sp = new StockPanel(this, stocks.getStock(i), x, y, sm);
            add(sp);
            spList.add(sp);
        }
    }

    public List<StockPanel> getStockPanelList() {
        return spList;
    }

    // EFFECTS: shows popup allowing users to choose amount of stock to buy/sell
    public int chooseAmount() {
        return Integer.valueOf(JOptionPane.showInputDialog(sms,"enter amount: "));
    }
}

