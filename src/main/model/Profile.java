package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.util.HashMap;

// represents a user profile with a fund amount, profit, transaction history, and owned stocks
public class Profile implements Writable {
    private BigDecimal funds;
    private BigDecimal profit;
    private BigDecimal netWorth;
    private TransactionHistory transactionHistory;
    private HashMap<String, Integer> ownedStocks;

    // EFFECTS: constructs new user profile with $100 net worth, $0 profit, empty transaction history, and 0 shares of
    //          each stock
    public Profile(StockList stocks) {
        this.funds = new BigDecimal(100);
        this.profit = new BigDecimal(0);
        this.netWorth = new BigDecimal(100);
        this.transactionHistory = new TransactionHistory();
        this.ownedStocks = new HashMap<String, Integer>();
        for (int i = 0; i < stocks.getSize(); i++) {
            this.ownedStocks.put(stocks.getStock(i).getCompany(), 0);
        }
    }

    // EFFECTS: constructs new user with given information, for use when reading data from file
    public Profile(BigDecimal funds, BigDecimal profit, BigDecimal netWorth,
                   TransactionHistory transactionHistory, HashMap<String, Integer> ownedStocks) {
        this.funds = funds;
        this.profit = profit;
        this.netWorth = netWorth;
        this.transactionHistory = transactionHistory;
        this.ownedStocks = ownedStocks;
    }

    public BigDecimal getFunds() {
        return this.funds;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public BigDecimal getNetWorth() {
        return this.netWorth;
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
    // EFFECTS: edits net worth when new day
    public void changeNetWorth(BigDecimal change) {
        this.netWorth = netWorth.add(change);
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
            Transaction t = new Transaction(stock.getCompany(), stock.getPrice(), amount);
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
            Transaction t = new Transaction(stock.getCompany(), stock.getPrice(), amount);
            this.transactionHistory.addTransaction(t);
            String company = stock.getCompany();
            BigDecimal price = stock.getPrice();
            System.out.println("Sold " + Math.abs(amount) + " shares of " + company + " for $" + price + " each");
            changeOwnedStocks(stock, amount);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("funds", funds);
        json.put("profit", profit);
        json.put("net worth", netWorth);
        json.put("transaction history", transactionHistory.transactionsToJson());
        json.put("owned stocks", ownedStocks);

        return json;
    }
}
