package ui.components;

import model.Profile;
import ui.ProfilePanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class OwnedStocksTable extends AbstractTableModel {
    private JTable ownedStocksTable;
    private String[] columnNames = {"Stock", "Amount"};
    private Object[][] ownedStocks = {{"AAPL", "0"},
            {"GOOG", "0"},
            {"NVDA", "0"},
            {"AMZN", "0"},
            {"RIVN", "0"},
            {"TSLA", "0"}};

    public OwnedStocksTable(ProfilePanel pp) {
        ownedStocksTable = new JTable(ownedStocks, columnNames);
        pp.add(ownedStocksTable);
    }

    @Override
    public int getRowCount() {
        return ownedStocks.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ownedStocks[rowIndex][columnIndex];
    }

    public void changeValue(int amount, int row, int col) {
        ownedStocks[row][col] = amount;
        fireTableCellUpdated(row, col);
    }
}
