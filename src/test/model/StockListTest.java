package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockListTest {
    private StockList sl1;
    private StockList sl2;
    private Stock s1;
    private Stock s2;

    @BeforeEach
    void setup() {
        s1 = new Stock("MSFT", BigDecimal.valueOf(415));
        s2 = new Stock("AAPL", BigDecimal.valueOf(415));
        List<Stock> stocks = new ArrayList<>(Arrays.asList(s1, s2));
        sl1 = new StockList();
        sl2 = new StockList(stocks);
    }

    @Test
    void testConstructorOne() {
        assertEquals(0, sl1.getSize());
    }

    @Test
    void testConstructorTwo() {
        assertEquals(2, sl2.getSize());
        assertEquals(s1, sl2.getStock(0));
    }

    @Test
    void testAddStock() {
        sl1.addStock(s1);
        assertEquals(1, sl1.getSize());
        assertEquals(s1, sl1.getStock(0));
    }

    @Test
    void testAddStockTwice() {
        sl1.addStock(s1);
        sl1.addStock(s2);
        assertEquals(2, sl1.getSize());
        assertEquals(s1, sl1.getStock(0));
        assertEquals(s2, sl1.getStock(1));
    }
}
