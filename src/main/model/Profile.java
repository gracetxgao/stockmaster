package model;

public class Profile {
    private String name;
    private int netWorth;
    private int profit;
    private TransactionHistory transactionHistory;

    public Profile(String name) {
        this.name = name;
        this.netWorth = 100;
        this.profit = 0;
        this.transactionHistory = new TransactionHistory();
    }

    public String getName() {
        return this.name;
    }

    public int getNetWorth() {
        return this.netWorth;
    }

    public int getProfit() {
        return this.profit;
    }

    public void viewTransactionHistory() {
        System.out.println(transactionHistory);
    }

    public void buyStock(Stock stock) {
        this.netWorth = this.netWorth - stock.getPrice();
        this.profit = this.profit - stock.getPrice();
        Transaction t = new Transaction(stock, (-1 * stock.getPrice()));
        this.transactionHistory.addTransaction(t);
    }

    public void sellStock(Stock stock) {
        this.netWorth = this.netWorth + stock.getPrice();
        this.profit = this.profit + stock.getPrice();
        Transaction t = new Transaction(stock, stock.getPrice());
        this.transactionHistory.addTransaction(t);
    }
}
