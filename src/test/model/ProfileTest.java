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
    private Stock s2;
    private List<Stock> stockList;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        s2  = new Stock("DIS", BigDecimal.valueOf(10));
        stockList = new ArrayList<>(Arrays.asList(s1));
        p1 = new Profile(stockList);
    }

    @Test
    void testConstructor() {
        assertEquals(BigDecimal.valueOf(100), p1.getNetWorth());
        assertEquals(BigDecimal.valueOf(0), p1.getProfit());
        HashMap<String, Integer> expectedOwnedStocks = new HashMap<String, Integer>();
        for (Stock s : stockList) {
            expectedOwnedStocks.put(s.getCompany(), 0);
        }
        assertEquals(expectedOwnedStocks, p1.getOwnedStocks());
        assertEquals(0, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testChangeOwnedStocks() {
        p1.changeOwnedStocks(s1, 5);
        assertEquals(5, p1.getOwnedStocks().get(s1.getCompany()));
    }

    @Test
    void testChangeOwnedStocksTwice() {
        p1.changeOwnedStocks(s1, 5);
        p1.changeOwnedStocks(s2, 3);
        assertEquals(5, p1.getOwnedStocks().get(s1.getCompany()));
        assertEquals(3, p1.getOwnedStocks().get(s2.getCompany()));
    }

    @Test
    void testBuyStock() {
        p1.buyStock(s2, 5);
        BigDecimal expectedNetWorth = BigDecimal.valueOf(50);
        BigDecimal expectedProfit = BigDecimal.valueOf(50).negate();
        assertEquals(expectedNetWorth, p1.getNetWorth());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(1, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testBuyStockMultiple() {
        p1.buyStock(s2, 5);
        p1.buyStock(s2, 3);
        BigDecimal expectedNetWorth = BigDecimal.valueOf(20);
        BigDecimal expectedProfit = BigDecimal.valueOf(80).negate();
        assertEquals(expectedNetWorth, p1.getNetWorth());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(2, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testSellStock() {
        p1.buyStock(s2, 5);
        p1.sellStock(s2, 1);
        BigDecimal expectedNetWorth = BigDecimal.valueOf(60);
        BigDecimal expectedProfit = BigDecimal.valueOf(40).negate();
        assertEquals(expectedNetWorth, p1.getNetWorth());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(2, p1.getTransactionHistory().getTransactionHistorySize());
    }

    @Test
    void testSellStockMultiple() {
        p1.buyStock(s2, 5);
        p1.buyStock(s2, 3);
        p1.sellStock(s2, 1);
        p1.sellStock(s2, 1);
        BigDecimal expectedNetWorth = BigDecimal.valueOf(40);
        BigDecimal expectedProfit = BigDecimal.valueOf(60).negate();
        assertEquals(expectedNetWorth, p1.getNetWorth());
        assertEquals(expectedProfit, p1.getProfit());
        assertEquals(4, p1.getTransactionHistory().getTransactionHistorySize());
    }
}
