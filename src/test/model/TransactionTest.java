package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction t1;
    private Stock s1;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        t1 = new Transaction(s1, BigDecimal.valueOf(415), 1);
    }

    @Test
    void testConstructor() {
        assertEquals(s1, t1.getStock());
        assertEquals(BigDecimal.valueOf(415), t1.getPrice());
        assertEquals(1, t1.getAmount());
    }
}
