package ui;

import model.Stock;
import model.StockMarket;
import ui.tools.BuyButton;
import ui.tools.SellButton;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class StockPanel extends JPanel {
    private static JLabel stockLabel;
    private JLabel stockPrice;
    private static JButton viewInfo;
    private static BuyButton buy;
    private static SellButton sell;
//    private static JLabel companyLogo;
    private StocksPanel sp;
//    public static final int STOCK_WIDTH = (int) (StocksPanel.STOCKS_WIDTH * (1 / 6));
//    public static final int STOCK_HEIGHT = (int) (StocksPanel.STOCKS_HEIGHT * (1 / 3));
    public static final int STOCK_WIDTH = 150;
    public static final int STOCK_HEIGHT = 200;
    private Stock s;
    private StockMarket sm;

    public StockPanel(StocksPanel sp, Stock s, int x, int y, StockMarket sm) {
        this.sm = sm;
        setBorder(BorderFactory.createLineBorder(Color.black));
        stockLabel = new JLabel(s.getCompany());
        stockPrice = new JLabel();
        stockPrice.setText(s.getPrice().toString());
        viewInfo = new JButton("view info");
        this.s = s;
        buy = new BuyButton(sm, this);
        sell = new SellButton(sm, this);
        add(stockLabel);
        add(stockPrice);
        add(viewInfo);
//        add(buy);
//        add(sell);
        setPreferredSize(new Dimension(STOCK_WIDTH, STOCK_HEIGHT));
        this.sp = sp;
        setLocation(x, y);
    }

    public void setStockPriceLabel(BigDecimal price) {
        this.stockPrice.setText(price.toString());
    }

    public Stock getStock() {
        return s;
    }
}
