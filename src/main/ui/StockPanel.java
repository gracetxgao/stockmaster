package ui;

import model.Stock;
import ui.components.BuyButton;
import ui.components.GraphPanel;
import ui.components.SellButton;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

// represents stock panel for a specific stock
public class StockPanel extends JPanel {
    private static JLabel stockLabel;
    private JLabel stockPrice;
    private static BuyButton buy;
    private static SellButton sell;
    private StocksPanel sp;
    public static final int STOCK_WIDTH = 175;
    public static final int STOCK_HEIGHT = 300;
    private Stock stock;
    private StockMarket sm;
    private GraphPanel gp;

    // EFFECTS: constructs stock panel at given location with stock information and graph
    public StockPanel(StocksPanel sp, Stock s, int x, int y, StockMarket sm) {
        this.sm = sm;
        setBorder(BorderFactory.createLineBorder(Color.black));
        stockLabel = new JLabel(s.getCompany());
        stockPrice = new JLabel();
        stockPrice.setText(s.getPrice().toString());
        this.stock = s;
        buy = new BuyButton(sm, this);
        sell = new SellButton(sm, this);
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

    // MODIFIES: this
    // EFFECTS: sets stock price label with given price
    public void setStockPriceLabel(BigDecimal price) {
        this.stockPrice.setText(price.toString());
    }

    public Stock getStock() {
        return stock;
    }

    // MODIFIES: this
    // EFFECTS: updates information on graph
    public void updateGraph(List<BigDecimal> data) {
        gp.updateGraph(data);
    }

    // MODIFIES: this
    // EFFECTS: loads information into graph
    public void loadGraph(List<BigDecimal> data) {
        gp.loadGraph(data);
    }
}
