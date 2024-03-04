package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    private Stock s1;
    private Stock s2;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        List<BigDecimal> priceHistory =
                new ArrayList<>(Arrays.asList(BigDecimal.valueOf(205.01), BigDecimal.valueOf(197.54)));
        s2 = new Stock("AAPL", BigDecimal.valueOf(200), priceHistory);
    }

    @Test
    void testConstructorOne() {
        assertEquals("MSFT", s1.getCompany());
        assertEquals(BigDecimal.valueOf(415), s1.getPrice());
    }

    @Test
    void testConstructorTwo() {
        assertEquals("AAPL", s2.getCompany());
        assertEquals(BigDecimal.valueOf(200), s2.getPrice());
        List<BigDecimal> expectedPriceHistory =
                new ArrayList<>(Arrays.asList(BigDecimal.valueOf(205.01), BigDecimal.valueOf(197.54)));
        assertEquals(expectedPriceHistory, s2.viewHistory());
    }

    @Test
    void testViewHistory() {
        assertEquals(1, s1.viewHistory().size());
        s1.getNewPrice(0.01);
        s1.getNewPrice(0.01);
        s1.getNewPrice(0.01);
        assertEquals(4, s1.viewHistory().size());
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
