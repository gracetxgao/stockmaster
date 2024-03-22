package ui;

import model.Stock;
import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class StockPanel extends JPanel {
    private static JLabel stockLabel;
    private StocksPanel sp;
    public static final int STOCK_WIDTH = (int) (StocksPanel.STOCKS_WIDTH * (1 / 6));
    public static final int STOCK_HEIGHT = (int) (StocksPanel.STOCKS_HEIGHT * (1 / 3));

    public StockPanel(StocksPanel sp, Stock s, int x, int y) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        stockLabel = new JLabel(s.getCompany());
        add(stockLabel);
        setPreferredSize(new Dimension(STOCK_WIDTH, STOCK_HEIGHT));
        this.sp = sp;
        setLocation(x, y);
    }
}
