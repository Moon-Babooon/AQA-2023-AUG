package org.prog.db;

import org.prog.util.BrowserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class BeanConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dmds =
                new DriverManagerDataSource(getSQLHost(), "user", "password");
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        return dmds;
    }

    private String getSQLHost() {
        String type = System.getProperty("driver.type", "DOCKER");
        BrowserType browserType = BrowserType.valueOf(type);
        if (BrowserType.DOCKER.equals(browserType)) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        }
        return "jdbc:mysql://localhost:3306/db";
    }
}