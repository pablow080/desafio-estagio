package org.desafioestagio.backend;

import jakarta.servlet.Filter;
import org.desafioestagio.backend.config.CustomWicketFilter;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.Page;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.desafioestagio.wicket.pages.IndexPage;

@SpringBootApplication(scanBasePackages = {"org.desafioestagio.backend", "org.desafioestagio.wicket"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    // Configuração do filtro Wicket
    @Bean
    public FilterRegistrationBean<Filter> wicketFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CustomWicketFilter());  // Usando o filtro customizado
        registration.addUrlPatterns("/wicket/*");  // URL para as páginas Wicket
        registration.setName("WicketFilter");
        registration.setOrder(1);
        return registration;
    }

    // Aplicação Wicket
    @Bean
    public WebApplication webApplication() {
        return new WebApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return IndexPage.class;  // Página inicial
            }

            @Override
            public void init() {
                super.init();
                getComponentInstantiationListeners().add(new SpringComponentInjector(this));  // Injeção de dependências
            }
        };
    }
}
