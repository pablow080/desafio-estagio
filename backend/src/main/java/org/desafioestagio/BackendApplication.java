package org.desafioestagio.backend;

import org.desafioestagio.backend.config.DataSourceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceConfig.class)
public class BackendApplication implements CommandLineRunner {

    private final DataSourceConfig dataSourceConfig;

    public BackendApplication(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Remover a impressão da senha por questões de segurança
        System.out.println("Database URL: " + dataSourceConfig.getUrl());
        System.out.println("User: " + dataSourceConfig.getUsername());
    }
}
