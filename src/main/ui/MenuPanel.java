package ui;

import ui.components.NextButton;
import ui.components.ReloadButton;
import ui.components.SaveButton;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private StockMarket sm;
    private StockMarketSimulator sms;
    private static NextButton next;
    private static SaveButton save;
    private static ReloadButton reload;
//    public static final int MENU_WIDTH = (int) (StockMarketSimulator.WIDTH);
//    public static final int MENU_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.2);
    public static final int MENU_WIDTH = 1000;
    public static final int MENU_HEIGHT = 100;

    public MenuPanel(StockMarket sm) {
        next = new NextButton(sm, this);
        save = new SaveButton(sm, this);
        reload = new ReloadButton(sm, this);

        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.sm = sm;
    }
}
