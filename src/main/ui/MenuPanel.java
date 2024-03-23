package ui;

import model.StockMarket;
import ui.tools.NextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private StockMarket sm;
    private StockMarketSimulator sms;
    private static NextButton next;
    private static JButton save;
    private static JButton reload;
//    public static final int MENU_WIDTH = (int) (StockMarketSimulator.WIDTH);
//    public static final int MENU_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.2);
    public static final int MENU_WIDTH = 1000;
    public static final int MENU_HEIGHT = 100;

    public MenuPanel(StockMarket sm) {
        next = new NextButton(sm, this);

        save = new JButton("save");
        reload = new JButton("reload");
        add(save);
        add(reload);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.sm = sm;
    }
}
