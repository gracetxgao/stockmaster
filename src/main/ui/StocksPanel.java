package ui;

import model.StockList;
import model.StockMarket;
import ui.components.AmountDialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StocksPanel extends JPanel {
    private static JLabel stocksLabel;
    private StockMarket sm;
    private StockList stocks;
//    public static final int STOCKS_WIDTH = (int) (StockMarketSimulator.WIDTH * 0.6);
//    public static final int STOCKS_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.8);
    public static final int STOCKS_WIDTH = (int) (StockMarketSimulator.WIDTH * 600);
    public static final int STOCKS_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 700);
    private List<StockPanel> spList;
    private AmountDialog amountChooser;
    private StockMarketSimulator sms;

    public StocksPanel(StockMarket sm, StockList stocks, StockMarketSimulator sms) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        stocksLabel = new JLabel("stocks");
        add(stocksLabel, BorderLayout.NORTH);
        setPreferredSize(new Dimension(STOCKS_WIDTH, STOCKS_HEIGHT));
        this.sm = sm;
        this.stocks = stocks;
        spList = new ArrayList<>();
        makeStockPanels();
        this.sms = sms;
    }

    public void updateStocks(StockList sl) {
        for (int i = 0; i < stocks.getSize(); i++) {
            spList.get(i).setStock(sl.getStock(i));
        }
    }

    public void makeStockPanels() {
        for (int i = 0; i < stocks.getSize(); i++) {
            int x = (i % 3) * 200;
            int y = i * 200;
//            int x = i * StockPanel.STOCK_WIDTH;
//            int y = i * StockPanel.STOCK_HEIGHT;
            StockPanel sp = new StockPanel(this, stocks.getStock(i), x, y, sm);
            add(sp);
            spList.add(sp);
        }
    }

    public List<StockPanel> getStockPanelList() {
        return spList;
    }

    public int chooseAmount() {
        return Integer.valueOf(JOptionPane.showInputDialog(sms,"enter amount: "));
    }
}

