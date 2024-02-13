package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AmazonTest {
    private Amazon amazon;

    @BeforeEach
    void setup() {
        amazon = new Amazon("AMZN", BigDecimal.valueOf(175.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("AMZN", amazon.getCompany());
        assertEquals(BigDecimal.valueOf(175.00), amazon.getPrice());
    }
}
