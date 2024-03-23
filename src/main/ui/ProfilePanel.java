package ui;

import model.Profile;
import model.StockMarket;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JLabel profileLabel;
    private JLabel netWorth;
    private JLabel profit;
    private JLabel funds;
    private StockMarket sm;
//    public static final int PROFILE_WIDTH = (int) (StockMarketSimulator.WIDTH * 0.4);
//    public static final int PROFILE_HEIGHT = (int) (StockMarketSimulator.HEIGHT * 0.8);
    public static final int PROFILE_WIDTH = 400;
    public static final int PROFILE_HEIGHT = 700;

    public ProfilePanel(StockMarket sm) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        profileLabel = new JLabel("profile");
        netWorth = new JLabel("");
        profit = new JLabel();
        funds = new JLabel();
        add(profileLabel);
        add(netWorth);
        add(profit);
        add(funds);
        setPreferredSize(new Dimension(PROFILE_WIDTH, PROFILE_HEIGHT));
        this.sm = sm;
    }

    public void setNetWorthLabel(String netWorth) {
        this.netWorth.setText(netWorth);
    }
}
