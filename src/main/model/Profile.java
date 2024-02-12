package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

// represents a user profile with a net worth, profit, and transaction history
public class Profile {
    private BigDecimal netWorth;
    private BigDecimal profit;
    private TransactionHistory transactionHistory;
    private HashMap<String, Integer> ownedStocks;

    // EFFECTS: constructs new user profile with $100 net worth, $0 profit, empty transaction history, and 0 shares of
    //          each stock
    public Profile(List<Stock> stockList) {
        this.netWorth = new BigDecimal(100);
        this.profit = new BigDecimal(0);
        this.transactionHistory = new TransactionHistory();
        this.ownedStocks = new HashMap<String, Integer>();
        for (Stock s : stockList) {
            this.ownedStocks.put(s.getCompany(), 0);
        }
    }

    public BigDecimal getNetWorth() {
        return this.netWorth;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public HashMap<String, Integer> getOwnedStocks() {
        return this.ownedStocks;
    }

    // MODIFIES: this
    // EFFECTS: edits number of shares owned of a stock
    public void changeOwnedStocks(Stock stock, int amount) {
        this.ownedStocks.put(stock.getCompany(), ownedStocks.get(stock.getCompany()) + amount);
    }

    // EFFECTS: prints transaction history
    public void viewTransactionHistory() {
        for (Transaction t : transactionHistory.getTransactionHistory()) {
            int amt = t.getAmount();
            String company = t.getStock().getCompany();
            BigDecimal price = t.getPrice();
            BigDecimal sellPrice = price.multiply(BigDecimal.valueOf(-1));
            if (price.compareTo(BigDecimal.valueOf(0)) == 1) {
                System.out.println("Bought " + amt + " shares of " + company + " for $" + price);
            } else {
                System.out.println("Sold " + amt + " shares of " + company + " for $" + sellPrice);
            }
        }
    }

    // REQUIRES: user's net worth is more than the stock price
    // MODIFIES: this
    // EFFECTS: subtracts price from net worth and profit and adds transaction to history
    //          (negative price represents buying)
    public void buyStock(Stock stock, int amount) {
        this.netWorth = this.netWorth.subtract(stock.getPrice());
        this.profit = this.profit.subtract(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice().negate(), amount);
        this.transactionHistory.addTransaction(t);
        System.out.println("Bought " + amount + " shares of " + stock.getCompany() + " for $" + stock.getPrice());
    }

    // REQUIRES: user has enough shares of the stock they choose to sell
    // MODIFIES: this
    // EFFECTS: adds price to net worth and profit and adds transaction to history
    //          (positive price represents selling)
    public void sellStock(Stock stock, int amount) {
        this.netWorth = this.netWorth.add(stock.getPrice());
        this.profit = this.profit.add(stock.getPrice());
        Transaction t = new Transaction(stock, stock.getPrice(), amount);
        this.transactionHistory.addTransaction(t);
        System.out.println("Sold " + amount + " shares of " + stock.getCompany() + " for $" + stock.getPrice());
    }
}
