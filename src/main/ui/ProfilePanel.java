package ui;

import model.Profile;
import model.TransactionHistory;
import ui.components.TransactionsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilePanel extends JPanel {
    private JLabel netWorth;
    private JLabel profit;
    private JLabel funds;
    private StockMarket sm;
    public static final int PROFILE_WIDTH = 400;
    public static final int PROFILE_HEIGHT = 700;
    private TransactionsPanel transactionsPanel;
    private Profile profile;
    private DefaultTableModel ownedStocksTable;

    public ProfilePanel(StockMarket sm, Profile p) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        netWorth = new JLabel();
        setNetWorthLabel(p.getNetWorth());
        profit = new JLabel();
        setProfitLabel(p.getProfit());
        funds = new JLabel();
        setFundsLabel(p.getFunds());
        add(netWorth, BorderLayout.NORTH);
        add(profit, BorderLayout.CENTER);
        add(funds, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(PROFILE_WIDTH, PROFILE_HEIGHT));
        this.sm = sm;
        transactionsPanel = new TransactionsPanel();
        add(transactionsPanel);
        this.profile = p;
        makeOwnedStocksTable(p.getOwnedStocks());
    }

    public void makeOwnedStocksTable(HashMap<String, Integer> ownedStocks) {
        ownedStocksTable = new DefaultTableModel(
                new Object[] {"stock", "amount"}, 0
        );
        for (Map.Entry<String, Integer> entry : ownedStocks.entrySet()) {
            ownedStocksTable.addRow(new Object[]{ entry.getKey(), entry.getValue() });
        }
        JTable table = new JTable(ownedStocksTable);
        add(table);
    }

    public void updateOwnedStocksTable(HashMap<String, Integer> ownedStocks) {
        List<String> keys = new ArrayList<>(ownedStocks.keySet());
        for (int i = 0; i < ownedStocks.size(); i++) {
            Integer prev = (Integer) ownedStocksTable.getValueAt(i, 1);
            Integer curr = ownedStocks.get(keys.get(i));
            if (!prev.equals(curr)) {
                ownedStocksTable.setValueAt(curr, i, 1);
            }
        }
    }

    public void setNetWorthLabel(BigDecimal netWorth) {
        this.netWorth.setText("net worth: " + netWorth.toString());
    }

    public void setProfitLabel(BigDecimal profit) {
        this.profit.setText("profit: " + profit.toString());
    }

    public void setFundsLabel(BigDecimal funds) {
        this.funds.setText("funds: " + funds.toString());
    }

    public void setTransactionHistoryList(TransactionHistory transactionHistory) {
        transactionsPanel.setTransactionHistoryList(transactionHistory);
    }

    public void addTransaction(String transaction) {
        transactionsPanel.addTransaction(transaction);
    }
}
