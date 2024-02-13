package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleTest {
    private Google google;

    @BeforeEach
    void setup() {
        google = new Google("GOOG", BigDecimal.valueOf(125.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("GOOG", google.getCompany());
        assertEquals(BigDecimal.valueOf(125.00), google.getPrice());
    }
}

