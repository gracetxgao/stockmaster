package persistence;

import model.Profile;
import model.Stock;
import model.TransactionHistory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProfile(BigDecimal funds, BigDecimal profit, BigDecimal netWorth,
                                TransactionHistory transactionHistory, HashMap<String, Integer> ownedStocks,
                                Profile profile) {
        assertEquals(funds, profile.getFunds());
        assertEquals(profit, profile.getProfit());
        assertEquals(netWorth, profile.getNetWorth());
        assertEquals(transactionHistory.getTransactionHistorySize(),
                profile.getTransactionHistory().getTransactionHistorySize());
        assertEquals(ownedStocks, profile.getOwnedStocks());
    }

    protected void checkStock(String company, BigDecimal price, List<BigDecimal> priceHistory, Stock stock) {
        assertEquals(company, stock.getCompany());
        assertEquals(price, stock.getPrice());
        assertEquals(priceHistory, stock.viewHistory());
    }
}
