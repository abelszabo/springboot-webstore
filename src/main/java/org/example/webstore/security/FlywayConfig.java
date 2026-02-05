package org.example.webstore.security;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

//    @Bean
//    public Flyway flyway(DataSource dataSource) {
//        System.out.println("configuring flyway: " + dataSource);
//        Flyway flyway = Flyway.configure()
//                .dataSource(dataSource)
//                //.databaseType("postgresql")
//                .baselineOnMigrate(true)
//                .load();
//
//        System.out.println("flyway migrate...");
//        flyway.migrate();
//        // Unsupported Database: PostgreSQL 18.1
//
//        return flyway;
//    }
}