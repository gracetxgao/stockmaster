package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class NvidiaTest {
    private Nvidia nvidia;

    @BeforeEach
    void setup() {
        nvidia = new Nvidia("NVDA", BigDecimal.valueOf(720.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("NVDA", nvidia.getCompany());
        assertEquals(BigDecimal.valueOf(720.00), nvidia.getPrice());
    }
}