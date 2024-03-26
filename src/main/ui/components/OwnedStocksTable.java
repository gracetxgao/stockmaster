package ui.components;

import model.Profile;
import ui.ProfilePanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class OwnedStocksTable {
//    public TableModel makeOwnedStocksTable(HashMap<String, Integer> ownedStocks) {
//        DefaultTableModel ownedStocksTable = new DefaultTableModel(
//                new Object[] {"stock", "amount"}, 0
//        );
//        for (Map.Entry<String, Integer> entry : ownedStocks.entrySet()) {
//            ownedStocksTable.addRow(new Object[]{ entry.getKey(), entry.getValue() });
//        }
//    }

//    private JTable ownedStocksTable;
//    private String[] columnNames = new String[]{"Stock", "Amount"};
//    private Object[][] ownedStocks = new Object[][];
//
//            {{"AAPL", "0"},
//            {"GOOG", "0"},
//            {"NVDA", "0"},
//            {"AMZN", "0"},
//            {"RIVN", "0"},
//            {"TSLA", "0"}};
//
//    public OwnedStocksTable(ProfilePanel pp) {
//        ownedStocksTable = new JTable(ownedStocks, columnNames);
//        pp.add(ownedStocksTable);
//    }
//
//    @Override
//    public int getRowCount() {
//        return ownedStocks.length;
//    }
//
//    @Override
//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return ownedStocks[rowIndex][columnIndex];
//    }
//
//    public void changeValue(int amount, int row, int col) {
//        ownedStocks[row][col] = amount;
//        fireTableCellUpdated(row, col);
//    }
}
