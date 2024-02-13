package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RivianTest {
    private Rivian rivian;

    @BeforeEach
    void setup() {
        rivian = new Rivian("RIVN", BigDecimal.valueOf(20.00));
    }

    @Test
    void TestConstructor() {
        assertEquals("RIVN", rivian.getCompany());
        assertEquals(BigDecimal.valueOf(20.00), rivian.getPrice());
    }
}

