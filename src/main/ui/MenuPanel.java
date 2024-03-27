package ui;

import ui.components.NextButton;
import ui.components.ReloadButton;
import ui.components.SaveButton;

import javax.swing.*;
import java.awt.*;

// represents panel used to hold next, save, and load buttons
public class MenuPanel extends JPanel {
    private StockMarket sm;
    private static NextButton next;
    private static SaveButton save;
    private static ReloadButton reload;
    public static final int MENU_WIDTH = 1000;
    public static final int MENU_HEIGHT = 100;

    // EFFECTS: constructs menu panel
    public MenuPanel(StockMarket sm) {
        next = new NextButton(sm, this);
        save = new SaveButton(sm, this);
        reload = new ReloadButton(sm, this);

        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.sm = sm;
    }
}
