package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile p1;
    private Stock s1;
    private Stock s3;
    private Transaction t1;
    private TransactionHistory th;
    private List<Stock> stockList;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        s3 = new Stock("BA", BigDecimal.valueOf(10));
        t1 = new Transaction(s1, BigDecimal.valueOf(415), 1);
        th = new TransactionHistory();
        th.addTransaction(t1);
        stockList = new ArrayList<>(Arrays.asList(s1, s3));
        p1 = new Profile(stockList);
    }

    @Test
    void testConstructor() {
        assertEquals(BigDecimal.valueOf(100), p1.getFunds());
        assertEquals(BigDecimal.valueOf(0), p1.getProfit());
        HashMap<String, Integer> expectedOwnedStocks = new HashMap<String, Integer>();
        for (Stock s : stockList) {
            expectedOwnedStocks.put(s.getCompany(), 0);
        }
        assertEquals(expectedOwnedStocks, p1.getOwnedStocks());
        assertEquals(0, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testViewTransactionHistory() {
        int amount = t1.getAmount();
        BigDecimal price = t1.getPrice();
        System.out.println("Bought " + amount + " shares of MSFT for $" + price + " each");
        p1.viewTransactionHistory();
    }

    @Test
    void testChangeOwnedStocks() {
        p1.changeOwnedStocks(s1, 5);
        assertEquals(5, p1.getOwnedStocks().get(s1.getCompany()));
    }

    @Test
    void testChangeOwnedStocksTwice() {
        p1.changeOwnedStocks(s1, 5);
        p1.changeOwnedStocks(s1, 3);
        assertEquals(8, p1.getOwnedStocks().get(s1.getCompany()));
    }

    @Test
    void testChangeOwnedStocksRemove() {
        p1.changeOwnedStocks(s1, 5);
        p1.changeOwnedStocks(s1, -2);
        assertEquals(3, p1.getOwnedStocks().get(s1.getCompany()));
    }

    @Test
    void testChangeOwnedStocksRemoveTwice() {
        p1.changeOwnedStocks(s1, 5);
        p1.changeOwnedStocks(s1, 3);
        p1.changeOwnedStocks(s1, -1);
        p1.changeOwnedStocks(s1, -2);
        assertEquals(5, p1.getOwnedStocks().get(s1.getCompany()));
    }

    @Test
    void testBuyStock() {
        p1.buyStock(s3, 5);
        BigDecimal expectedFunds = BigDecimal.valueOf(50);
        BigDecimal expectedProfit = BigDecimal.valueOf(50).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(1, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testBuyStockNotEnough() {
        p1.buyStock(s1, 1);
        System.out.println("Insufficient funds");
        BigDecimal expectedFunds = BigDecimal.valueOf(100);
        BigDecimal expectedProfit = BigDecimal.valueOf(0).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(0, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testBuyStockMultiple() {
        p1.buyStock(s3, 5);
        p1.buyStock(s3, 3);
        BigDecimal expectedFunds = BigDecimal.valueOf(20);
        BigDecimal expectedProfit = BigDecimal.valueOf(80).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(2, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testSellStock() {
        p1.buyStock(s3, 5);
        p1.sellStock(s3, -1);
        BigDecimal expectedFunds = BigDecimal.valueOf(60);
        BigDecimal expectedProfit = BigDecimal.valueOf(40).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(2, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testSellStockNotEnough() {
        p1.buyStock(s3, 5);
        p1.sellStock(s3, -6);
        System.out.println("Not enough owned shares");
        BigDecimal expectedFunds = BigDecimal.valueOf(50);
        BigDecimal expectedProfit = BigDecimal.valueOf(50).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(1, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testSellStockMultiple() {
        p1.buyStock(s3, 5);
        p1.buyStock(s3, 3);
        p1.sellStock(s3, -1);
        p1.sellStock(s3, -1);
        BigDecimal expectedFunds = BigDecimal.valueOf(40);
        BigDecimal expectedProfit = BigDecimal.valueOf(60).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(4, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testBuyAndSell() {
        p1.buyStock(s3, 5);
        p1.buyStock(s3, 3);
        p1.buyStock(s3, 2);
        p1.sellStock(s3, -1);
        p1.sellStock(s3, -1);
        BigDecimal expectedFunds = BigDecimal.valueOf(20);
        BigDecimal expectedProfit = BigDecimal.valueOf(80).negate();
        assertEquals(expectedFunds, p1.getFunds());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(5, p1.getTransactionHistory().getTransactionHistorySize());
    }
}
