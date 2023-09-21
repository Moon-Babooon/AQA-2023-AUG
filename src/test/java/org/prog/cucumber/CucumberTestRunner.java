package org.prog.cucumber;


import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.prog.util.DataHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@EnableTransactionManagement
@EnableJpaRepositories("org.prog")
@CucumberContextConfiguration
@ContextConfiguration(locations = "classpath*:spring/spring-context.xml")
@ComponentScan(basePackages = {"org.prog"})
@CucumberOptions(features = "src/test/resources/features",
        glue = "org.prog",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() {

    }

    @BeforeMethod
    public void resetHolder() {
        DataHolder.reset();
    }

    @AfterSuite
    public void tearDown() {

    }
}
