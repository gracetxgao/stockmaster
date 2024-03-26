package ui.components;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionsFilter implements ActionListener {
    private static JTextField text;
    private String target;
    private TransactionsList transactionsList;


    public TransactionsFilter(TransactionsPanel tp, TransactionsList transactionsList) {
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
