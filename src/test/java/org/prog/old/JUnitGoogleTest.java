package org.prog.old;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class JUnitGoogleTest {

    @BeforeAll
    public static void setUp() {
        System.out.println("=============START===============");
    }

    @BeforeEach
    public void beforeTest() {
        System.out.println(">>>>>>");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("<<<<<<");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("=============END===============");
    }

    @Test
    public void smth() {
        System.out.println("Junit test");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 2)
    public void testWithParams(String input, String expected) {
        System.out.println(input + expected);
    }
}
