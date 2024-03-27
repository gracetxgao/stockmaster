package ui;

import model.Stock;
import model.StockMarket;
import ui.components.BuyButton;
import ui.components.GraphPanel;
import ui.components.SellButton;
import ui.components.ViewInfoButton;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class StockPanel extends JPanel {
    private static JLabel stockLabel;
    private JLabel stockPrice;
    private static ViewInfoButton view;
    private static BuyButton buy;
    private static SellButton sell;
//    private static JLabel companyLogo;
    private StocksPanel sp;
//    public static final int STOCK_WIDTH = (int) (StocksPanel.STOCKS_WIDTH * (1 / 6));
//    public static final int STOCK_HEIGHT = (int) (StocksPanel.STOCKS_HEIGHT * (1 / 3));
    public static final int STOCK_WIDTH = 175;
    public static final int STOCK_HEIGHT = 300;
    private Stock stock;
    private StockMarket sm;
    private GraphPanel gp;

    public StockPanel(StocksPanel sp, Stock s, int x, int y, StockMarket sm) {
        this.sm = sm;
        setBorder(BorderFactory.createLineBorder(Color.black));
        stockLabel = new JLabel(s.getCompany());
        stockPrice = new JLabel();
        stockPrice.setText(s.getPrice().toString());
        this.stock = s;
        buy = new BuyButton(sm, this);
        sell = new SellButton(sm, this);
//        view = new ViewInfoButton(sm, this);
        add(stockLabel);
        add(stockPrice);

        setPreferredSize(new Dimension(STOCK_WIDTH, STOCK_HEIGHT));
        this.sp = sp;
        setLocation(x, y);
        gp = new GraphPanel(s.viewHistory(), this);
    }

    public void setStock(Stock s) {
        this.stock = s;
    }

    public void setStockPriceLabel(BigDecimal price) {
        this.stockPrice.setText(price.toString());
    }

    public Stock getStock() {
        return stock;
    }

    public void showGraph() {
        gp.addToStockPanel();
    }

    public void updateGraph(List<BigDecimal> data) {
        gp.updateGraph(data);
    }
}
