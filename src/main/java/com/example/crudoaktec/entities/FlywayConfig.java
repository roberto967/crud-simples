package com.example.crudoaktec.entities;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.flyway.locations}")
    private String flywayLocations;

    @PostConstruct
    public void migrateFlyway() {
        Flyway.configure()
                .dataSource(dataSource)
                .locations(flywayLocations)
                .load()
                .migrate();
    }
}
