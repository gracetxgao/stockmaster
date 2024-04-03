package model;

import org.json.JSONObject;
import persistence.Writable;
import ui.ProfilePanel;

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
        EventLog.getInstance().logEvent(new Event("amount of owned shares of "
                + stock.getCompany() + " changed to " + Math.abs(amount)));
    }

    // MODIFIES: this
    // EFFECTS: edits net worth on day change
    public void changeNetWorth(BigDecimal change) {
        this.netWorth = netWorth.add(change);
        EventLog.getInstance().logEvent(new Event("net worth changed to  "
                + netWorth));
    }

    // MODIFIES: this
    // EFFECTS: if user has enough money, subtracts price from funds, adds transaction to history,
    //          adds shares to owned stock
    //          returns true if successful and false if not
    public Boolean buyStock(Stock stock, int amount) {
        BigDecimal cost = stock.getPrice().multiply(BigDecimal.valueOf(amount));
        if (this.funds.compareTo(cost) == -1) {
            EventLog.getInstance().logEvent(new Event("attempted to buy "
                    + Math.abs(amount) + " shares of " + stock.getCompany() + " but failed due to insufficient funds"));
            return false;
        } else {
            this.funds = this.funds.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            this.profit = this.profit.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            Transaction t = new Transaction(stock.getCompany(), stock.getPrice(), amount);
            this.transactionHistory.addTransaction(t);
            String company = stock.getCompany();
            BigDecimal price = stock.getPrice();
            changeOwnedStocks(stock, amount);
            EventLog.getInstance().logEvent(new Event("bought "
                    + Math.abs(amount) + " shares of " + stock.getCompany()));
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: if user owns enough shares, adds price to funds, adds transaction to history,
    //          removes shares from owned stock
    //          returns true if successful and false if not
    public Boolean sellStock(Stock stock, int amount) {
        if (ownedStocks.get(stock.getCompany()) < Math.abs(amount)) {
            EventLog.getInstance().logEvent(new Event("attempted to sell "
                    + Math.abs(amount) + " shares of " + stock.getCompany() + " but failed due to insufficient amount owned"));
            return false;
        } else {
            this.funds = this.funds.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            this.profit = this.profit.subtract(stock.getPrice().multiply(BigDecimal.valueOf(amount)));
            Transaction t = new Transaction(stock.getCompany(), stock.getPrice(), amount);
            this.transactionHistory.addTransaction(t);
            String company = stock.getCompany();
            BigDecimal price = stock.getPrice();
            changeOwnedStocks(stock, amount);
            EventLog.getInstance().logEvent(new Event("sold "
                    + Math.abs(amount) + " shares of " + stock.getCompany()));
            return true;
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
