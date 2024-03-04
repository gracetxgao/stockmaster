package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    private StockList stocks;
    private Stock apple;
    private Stock google;
    private Stock nvidia;
    private Stock amazon;
    private Stock rivian;
    private Stock tesla;

    @BeforeEach
    void setup() {
        apple = new Stock("AAPL", BigDecimal.valueOf(150.00));
        google = new Stock("GOOG", BigDecimal.valueOf(125.00));
        nvidia = new Stock("NVDA", BigDecimal.valueOf(720.00));
        amazon = new Stock("AMZN", BigDecimal.valueOf(175.00));
        rivian = new Stock("RIVN", BigDecimal.valueOf(20.00));
        tesla = new Stock("TSLA", BigDecimal.valueOf(30.00));
        stocks = new StockList();
        stocks.addStock(apple);
        stocks.addStock(google);
        stocks.addStock(nvidia);
        stocks.addStock(amazon);
        stocks.addStock(rivian);
        stocks.addStock(tesla);
    }
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakefile.json");
        try {
            Profile p = reader.readProfile();
            fail("should have thrown IOException");
        } catch (IOException e) {
            System.out.println("successfully caught IOException");
        }
    }

    @Test
    void testReaderProfileInitial() {
        JsonReader reader = new JsonReader("./data/testReaderProfileInitial.json");
        try {
            Profile actual = reader.readProfile();
            Profile expected = new Profile(stocks);
            checkProfile(expected, actual);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderProfileOne() {
        JsonReader reader = new JsonReader("./data/testReaderProfileOne.json");
        try {
            Profile actual = reader.readProfile();
            TransactionHistory th = new TransactionHistory();
            Transaction t1 = new Transaction("RIVN", BigDecimal.valueOf(20), 1);
            Transaction t2 = new Transaction("RIVN", BigDecimal.valueOf(22.22), -1);
            Transaction t3 = new Transaction("TSLA", BigDecimal.valueOf(27.5), 1);
            th.addTransaction(t1);
            th.addTransaction(t2);
            th.addTransaction(t3);
            Profile temp = new Profile(stocks);
            temp.changeOwnedStocks(tesla, 1);
            HashMap<String, Integer> ownedStocks = temp.getOwnedStocks();
            Profile expected = new Profile(BigDecimal.valueOf(74.72), BigDecimal.valueOf(-25.28),
                    BigDecimal.valueOf(102.22), th, ownedStocks);
            checkProfile(expected, actual);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
