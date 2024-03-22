package ui;

import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private static JLabel profileLabel;
    private StockMarket sm;
    public static final int PROFILE_WIDTH = (int) (StockMarketSimulator.WIDTH * 0.4);
    public static final int PROFILE_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.8);

    public ProfilePanel(StockMarket sm) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        profileLabel = new JLabel("profile");
        add(profileLabel);
        setPreferredSize(new Dimension(PROFILE_WIDTH, PROFILE_HEIGHT));
        this.sm = sm;
    }
}
