package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/:illegal\0file+name.json");
            writer.open();
            fail("should have thrown IOException");
        } catch (IOException e) {
            System.out.println("IOException successfully caught");
        }
    }

    @Test
    void testWriterProfileInitial() {
        try {
            StockList sl = new StockList();
            Profile p = new Profile(sl);
            JsonWriter writer = new JsonWriter("./data/testWriterProfileInitial.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterProfileInitial.json");
            p = reader.readProfile();
            assertEquals(BigDecimal.valueOf(100), p.getFunds());
            assertEquals(BigDecimal.valueOf(0), p.getProfit());
        } catch (IOException e) {
            fail("should not have thrown exception");
        }
    }

    @Test
    void testWriterStocksInitial() {
        try {
            StockList sl = new StockList();
            JsonWriter writer = new JsonWriter("./data/testWriterProfileInitial.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterProfileInitial.json");
            sl = reader.readStockList();
            assertEquals(0, sl.getSize());
        } catch (IOException e) {
            fail("should not have thrown exception");
        }
    }

    @Test
    void testWriterProfileTwo() {
        try {
            StockList sl = new StockList();
            Stock s1 = new Stock("AAPL", BigDecimal.valueOf(20));
            sl.addStock(s1);
            Profile p = new Profile(sl);
            p.buyStock(s1, 2);
            JsonWriter writer = new JsonWriter("./data/testWriterProfileTwo.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterProfileTwo.json");
            p = reader.readProfile();
            TransactionHistory th = new TransactionHistory();
            Transaction t = new Transaction("AAPL", BigDecimal.valueOf(20), 2);
            th.addTransaction(t);
            HashMap<String, Integer> ownedStocks = new HashMap<String, Integer>();
            ownedStocks.put("AAPL", 2);
            checkProfile(BigDecimal.valueOf(60), BigDecimal.valueOf(-40), BigDecimal.valueOf(100), th, ownedStocks, p);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
