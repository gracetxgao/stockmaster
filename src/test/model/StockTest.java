package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    private Stock s1;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
    }

    @Test
    void testConstructor() {
        assertEquals("MSFT", s1.getCompany());
        assertEquals(BigDecimal.valueOf(415), s1.getPrice());
    }

    @Test
    void testViewHistory() {
        assertEquals(0, s1.viewHistory().size());
        s1.getNewPrice(0.01);
        s1.getNewPrice(0.01);
        s1.getNewPrice(0.01);
        assertEquals(3, s1.viewHistory().size());
    }

    @Test
    void testGetNewPriceIncrease() {
        s1.getNewPrice(0.1);
        BigDecimal expected = BigDecimal.valueOf(456.5).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, s1.getPrice());
    }

    @Test
    void testGetNewPriceIncreaseTwice() {
        s1.getNewPrice(0.1);
        s1.getNewPrice(0.2);
        BigDecimal expected = BigDecimal.valueOf(547.8).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, s1.getPrice());
    }

    @Test
    void testGetNewPriceDecrease() {
        s1.getNewPrice(-0.1);
        BigDecimal expected = BigDecimal.valueOf(373.5).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, s1.getPrice());
    }

    @Test
    void testGetNewPriceDecreaseTwice() {
        s1.getNewPrice(-0.1);
        s1.getNewPrice(-0.2);
        BigDecimal expected = BigDecimal.valueOf(298.8).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, s1.getPrice());
    }
}
