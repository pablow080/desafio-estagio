package org.desafioestagio.javabackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "org.desafioestagio.javabackend.model")
@EnableJpaRepositories(basePackages = "org.desafioestagio.javabackend.repository")
public class JavaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Aplicação iniciada com sucesso!");
        };
    }
}
