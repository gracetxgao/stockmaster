package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

// represents a user profile with a net worth, profit, and transaction history
public class Profile {
    private BigDecimal funds;
    private BigDecimal profit;
    private TransactionHistory transactionHistory;
    private HashMap<String, Integer> ownedStocks;

    // EFFECTS: constructs new user profile with $100 net worth, $0 profit, empty transaction history, and 0 shares of
    //          each stock
    public Profile(List<Stock> stockList) {
        this.funds = new BigDecimal(100);
        this.profit = new BigDecimal(0);
        this.transactionHistory = new TransactionHistory();
        this.ownedStocks = new HashMap<String, Integer>();
        for (Stock s : stockList) {
            this.ownedStocks.put(s.getCompany(), 0);
        }
    }

    public BigDecimal getFunds() {
        return this.funds;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public HashMap<String, Integer> getOwnedStocks() {
        return this.ownedStocks;
    }

    public TransactionHistory getTransactionHistory() {
        return this.transactionHistory;
    }

    // MODIFIES: this
    // EFFECTS: edits number of shares owned of a stock
    public void changeOwnedStocks(Stock stock, int amount) {
        this.ownedStocks.put(stock.getCompany(), ownedStocks.get(stock.getCompany()) + amount);
    }

    // MODIFIES: this
    // EFFECTS: if user has enough money, subtracts price from funds, adds transaction to history,
    //          adds shares to owned stock
    public void buyStock(Stock stock, int amount) {
        BigDecimal cost = stock.getPrice().multiply(BigDecimal.valueOf(amount));
        if (this.funds.compareTo(cost) == -1) {
            System.out.println("Insufficient funds");
        } else {
            this.funds = this.funds.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            this.profit = this.profit.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            Transaction t = new Transaction(stock, stock.getPrice(), amount);
            this.transactionHistory.addTransaction(t);
            String company = stock.getCompany();
            BigDecimal price = stock.getPrice();
            System.out.println("Bought " + amount + " shares of " + company + " for $" + price + " each");

            changeOwnedStocks(stock, amount);
        }
    }

    // MODIFIES: this
    // EFFECTS: if user owns enough shares, adds price to funds, adds transaction to history,
    //          removes shares from owned stock
    public void sellStock(Stock stock, int amount) {
        if (ownedStocks.get(stock.getCompany()) < Math.abs(amount)) {
            System.out.println("Not enough owned shares");
        } else {
            this.funds = this.funds.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            this.profit = this.profit.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            Transaction t = new Transaction(stock, stock.getPrice(), amount);
            this.transactionHistory.addTransaction(t);
            String company = stock.getCompany();
            BigDecimal price = stock.getPrice();
            System.out.println("Sold " + Math.abs(amount) + " shares of " + company + " for $" + price + " each");
            changeOwnedStocks(stock, amount);
        }
    }
}
