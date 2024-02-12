package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestTransactionHistory {
    private TransactionHistory th;
    private Transaction t1;
    private Stock s1;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        th = new TransactionHistory();
        t1 = new Transaction(s1, BigDecimal.valueOf(415), 1);
    }

    @Test
    void testConstructor() {
        assertEquals(0, th.getTransactionHistorySize());
    }

    @Test
    void testAddTransaction() {
        th.addTransaction(t1);
        assertEquals(1, th.getTransactionHistorySize());
    }

    @Test
    void testAddTransactionTwice() {
        th.addTransaction(t1);
        th.addTransaction(t1);
        assertEquals(2, th.getTransactionHistorySize());
    }
}
