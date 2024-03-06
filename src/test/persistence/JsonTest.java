package persistence;

import model.Profile;
import model.Stock;
import model.TransactionHistory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProfile(Profile expected, Profile actual) {
        assertEquals(expected.getFunds(), actual.getFunds());
        assertEquals(expected.getProfit(), actual.getProfit());
        assertEquals(expected.getNetWorth(), actual.getNetWorth());
        assertEquals(expected.getTransactionHistory().getTransactionHistorySize(),
                actual.getTransactionHistory().getTransactionHistorySize());
        assertEquals(expected.getOwnedStocks(), actual.getOwnedStocks());
    }

    protected void checkStock(Stock expected, Stock actual) {
        assertEquals(expected.getCompany(), actual.getCompany());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.viewHistory(), actual.viewHistory());
    }
}
