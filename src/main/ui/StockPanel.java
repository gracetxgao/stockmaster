package ui;

import model.Stock;
import model.StockMarket;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class StockPanel extends JPanel {
    private static JLabel stockLabel;
    private JLabel stockPrice;
    private static JButton viewInfo;
    private static JButton buy;
    private static JButton sell;
//    private static JLabel companyLogo;
    private StocksPanel sp;
//    public static final int STOCK_WIDTH = (int) (StocksPanel.STOCKS_WIDTH * (1 / 6));
//    public static final int STOCK_HEIGHT = (int) (StocksPanel.STOCKS_HEIGHT * (1 / 3));
    public static final int STOCK_WIDTH = 150;
    public static final int STOCK_HEIGHT = 200;
    private Stock s;

    public StockPanel(StocksPanel sp, Stock s, int x, int y) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        stockLabel = new JLabel(s.getCompany());
        stockPrice = new JLabel();
        stockPrice.setText(s.getPrice().toString());
        viewInfo = new JButton("view info");
        buy = new JButton("buy");
        sell = new JButton("sell");
        add(stockLabel);
        add(stockPrice);
        add(viewInfo);
        add(buy);
        add(sell);
        setPreferredSize(new Dimension(STOCK_WIDTH, STOCK_HEIGHT));
        this.sp = sp;
        setLocation(x, y);
        this.s = s;
    }

    public void setStockPriceLabel(BigDecimal price) {
        this.stockPrice.setText(price.toString());
    }
}
