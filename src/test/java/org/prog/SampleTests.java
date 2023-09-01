package org.prog;

import org.testng.annotations.Test;
import org.testng.annotations.*;

public class SampleTests {

    @BeforeSuite
    public void setUp() {
        System.out.println("=======START SUITE======");
    }

    @BeforeTest
    public void onTest() {
        System.out.println("Start test");
    }

    @BeforeMethod
    public void beforeEach() {
        System.out.println("About to run test");
    }

    @Test
    public void test1() {
        System.out.println("this is test 1!");
    }

    @Test
    public void test2() {
        System.out.println("this is test 2!");
    }

    @Test
    public void test3() {
        System.out.println("this is test 3!");
    }

    @Test(dataProvider = "stringAndInt")
    public void paramterizedTest(String s, int i) {
        if (i > 2) {
            throw new RuntimeException("fail test");
        }
        System.out.println("All is OK for " + s + " " + i);
    }

    @DataProvider(name = "stringAndInt")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {"a", 1},
                {"b", 2},
                {"c", 3},
                {"d", 4}
        };
    }

        @AfterMethod
    public void afterEach() {
        System.out.println("Finishing run test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("End test");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("=======END SUITE======");
    }
}
