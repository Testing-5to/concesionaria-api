package com.autos.concesionaria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConcesionariaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        int a = 2;
        int b = 2;

        assertEquals(4, a + b);
    }

}
