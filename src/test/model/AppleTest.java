package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AppleTest {
    private Apple apple;

    @BeforeEach
    void setup() {
        apple = new Apple("AAPL", BigDecimal.valueOf(150.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("AAPL", apple.getCompany());
        assertEquals(BigDecimal.valueOf(150.00), apple.getPrice());
    }
}
