package org.desafioestagio.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    // Getters e setters
    private String url;
    private String username;
    private String password;

}
