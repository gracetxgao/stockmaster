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
    private TransactionHistory th1;
    private StockList stocks1;

    private Profile p2;
    private Transaction t2;
    private TransactionHistory th2;
    private HashMap<String, Integer> ownedStocks;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        s3 = new Stock("BA", BigDecimal.valueOf(10));
        t1 = new Transaction(s1.getCompany(), BigDecimal.valueOf(415), 1);
        th1 = new TransactionHistory();
        th1.addTransaction(t1);
        stocks1 = new StockList();
        stocks1.addStock(s1);
        stocks1.addStock(s3);
        p1 = new Profile(stocks1);

        t2 = new Transaction(s3.getCompany(), BigDecimal.valueOf(12), -2);
        th2 = new TransactionHistory();
        th2.addTransaction(t1);
        th2.addTransaction(t2);
        ownedStocks = new HashMap<String, Integer>();
        ownedStocks.put(s1.getCompany(), 3);
        ownedStocks.put(s3.getCompany(), 5);
        p2 = new Profile(BigDecimal.valueOf(120), BigDecimal.valueOf(10), BigDecimal.valueOf(300), th2, ownedStocks);
    }

    @Test
    void testConstructorOne() {
        assertEquals(BigDecimal.valueOf(100), p1.getFunds());
        assertEquals(BigDecimal.valueOf(0), p1.getProfit());
        assertEquals(BigDecimal.valueOf(100), p1.getNetWorth());
        HashMap<String, Integer> expectedOwnedStocks = new HashMap<String, Integer>();
        for (int i = 0; i < stocks1.getSize(); i++) {
            expectedOwnedStocks.put(stocks1.getStock(i).getCompany(), 0);
        }
        assertEquals(expectedOwnedStocks, p1.getOwnedStocks());
        assertEquals(0, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testConstructorTwo() {
        assertEquals(BigDecimal.valueOf(120), p2.getFunds());
        assertEquals(BigDecimal.valueOf(10), p2.getProfit());
        assertEquals(BigDecimal.valueOf(300), p2.getNetWorth());
        HashMap<String, Integer> expectedOwnedStocks = new HashMap<String, Integer>();
        expectedOwnedStocks.put(s1.getCompany(), 3);
        expectedOwnedStocks.put(s3.getCompany(), 5);
        assertEquals(expectedOwnedStocks, p2.getOwnedStocks());
        assertEquals(2, p2.getTransactionHistory().getTransactionHistorySize());
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
    void testChangeNetWorth() {
        p2.changeNetWorth(BigDecimal.valueOf(10.07));
        p2.changeNetWorth(BigDecimal.valueOf(-25));
        assertEquals(BigDecimal.valueOf(285.07), p2.getNetWorth());
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
