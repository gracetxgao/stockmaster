package model;

import java.math.BigDecimal;

public class Profile {
    private String name;
    private BigDecimal netWorth;
    private BigDecimal profit;
    private TransactionHistory transactionHistory;

    public Profile(String name) {
        this.name = name;
        this.netWorth = new BigDecimal(100);
        this.profit = new BigDecimal(0);
        this.transactionHistory = new TransactionHistory();
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getNetWorth() {
        return this.netWorth;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public void viewTransactionHistory() {
        System.out.println(transactionHistory);
    }

    public void buyStock(Stock stock) {
        this.netWorth = this.netWorth.subtract(stock.getPrice());
        this.profit = this.profit.subtract(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice().negate());
        this.transactionHistory.addTransaction(t);
    }

    public void sellStock(Stock stock) {
        this.netWorth = this.netWorth.add(stock.getPrice());
        this.profit = this.profit.add(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice());
        this.transactionHistory.addTransaction(t);
    }
}
