package ui.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionsFilter implements ActionListener {
    private static JTextField text;
    private static JLabel filterLabel;
    private String target;
    private TransactionsList transactionsList;


    public TransactionsFilter(TransactionsPanel tp, TransactionsList transactionsList) {
        filterLabel = new JLabel("filter: ");
        tp.add(filterLabel);
        target = null;
        text = new JTextField(16);
        text.addActionListener(this);
        tp.add(text);
        this.transactionsList = transactionsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        target = text.getText();
        transactionsList.filterTransactions(target);
        System.out.println("updated filter: " + target);
    }
}
