package model;

import java.math.BigDecimal;

// represents an user profile with a net worth, profit, and transaction history
public class Profile {
    private BigDecimal netWorth;
    private BigDecimal profit;
    private TransactionHistory transactionHistory;

    // EFFECTS: constructs new user profile with $100 net worth and $0 profit and empty transaction history
    public Profile() {
        this.netWorth = new BigDecimal(100);
        this.profit = new BigDecimal(0);
        this.transactionHistory = new TransactionHistory();
    }

    public BigDecimal getNetWorth() {
        return this.netWorth;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    // EFFECTS: prints transaction history
    public void viewTransactionHistory() {
        System.out.println(transactionHistory);
    }

    // REQUIRES: user's net worth is more than the stock price
    // MODIFIES: this
    // EFFECTS: subtracts price from net worth and profit and adds transaction to history
    //          (negative price represents buying)
    public void buyStock(Stock stock) {
        this.netWorth = this.netWorth.subtract(stock.getPrice());
        this.profit = this.profit.subtract(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice().negate());
        this.transactionHistory.addTransaction(t);
    }

    // REQUIRES: user has enough shares of the stock they choose to sell
    // MODIFIES: this
    // EFFECTS: adds price to net worth and profit and adds transaction to history
    //          (positive price represents selling)
    public void sellStock(Stock stock) {
        this.netWorth = this.netWorth.add(stock.getPrice());
        this.profit = this.profit.add(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice());
        this.transactionHistory.addTransaction(t);
    }
}
