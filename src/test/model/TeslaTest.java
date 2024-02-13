package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TeslaTest {
    private Tesla tesla;

    @BeforeEach
    void setup() {
        tesla = new Tesla("TSLA", BigDecimal.valueOf(30.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("TSLA", tesla.getCompany());
        assertEquals(BigDecimal.valueOf(30.00), tesla.getPrice());
    }
}
