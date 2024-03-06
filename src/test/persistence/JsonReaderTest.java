package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
        apple = new Stock("AAPL", BigDecimal.valueOf(150));
        google = new Stock("GOOG", BigDecimal.valueOf(125));
        nvidia = new Stock("NVDA", BigDecimal.valueOf(720));
        amazon = new Stock("AMZN", BigDecimal.valueOf(175));
        rivian = new Stock("RIVN", BigDecimal.valueOf(20));
        tesla = new Stock("TSLA", BigDecimal.valueOf(30));
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
    void testReaderStocksInitial() {
        JsonReader reader = new JsonReader("./data/testReaderStocksInitial.json");
        try {
            StockList actual = reader.readStockList();
            StockList expected = stocks;
            for (int i = 0; i < actual.getSize(); i++) {
                checkStock(expected.getStock(i), actual.getStock(i));
            }
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

    @Test
    void testReaderStocksOne() {
        JsonReader reader = new JsonReader("./data/testReaderStocksOne.json");
        try {
            StockList actual = reader.readStockList();
            List<BigDecimal> appleHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(150),
                    BigDecimal.valueOf(154.47), BigDecimal.valueOf(146.66)));
            Stock apple = new Stock("AAPL", BigDecimal.valueOf(146.66), appleHistory);
            List<BigDecimal> googleHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(125),
                    BigDecimal.valueOf(134.29), BigDecimal.valueOf(128.01)));
            Stock google = new Stock("GOOG", BigDecimal.valueOf(128.01), googleHistory);
            List<BigDecimal> nvidiaHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(720),
                    BigDecimal.valueOf(764.4), BigDecimal.valueOf(733.07)));
            Stock nvidia = new Stock("NVDA", BigDecimal.valueOf(733.07), nvidiaHistory);
            List<BigDecimal> amazonHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(175),
                    BigDecimal.valueOf(168.44), BigDecimal.valueOf(160.18)));
            Stock amazon = new Stock("AMZN", BigDecimal.valueOf(160.18), amazonHistory);
            List<BigDecimal> rivianHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(20),
                    BigDecimal.valueOf(21.2), BigDecimal.valueOf(22.22)));
            Stock rivian = new Stock("RIVN", BigDecimal.valueOf(22.22), rivianHistory);
            List<BigDecimal> teslaHistory = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(30),
                    BigDecimal.valueOf(29.6), BigDecimal.valueOf(27.5)));
            Stock tesla = new Stock("TSLA", BigDecimal.valueOf(27.5), teslaHistory);
            List<Stock> expectedList = new ArrayList<>(Arrays.asList(apple, google, nvidia, amazon, rivian, tesla));
            StockList expected = new StockList(expectedList);
            for (int i = 0; i < actual.getSize(); i++) {
                checkStock(expected.getStock(i), actual.getStock(i));
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
