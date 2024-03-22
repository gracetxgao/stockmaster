package ui;

import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private StockMarket sm;
    public static final int MENU_WIDTH = (int) (StockMarketSimulator.WIDTH);
    public static final int MENU_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.2);

    public MenuPanel(StockMarket sm) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.sm = sm;
    }
}
