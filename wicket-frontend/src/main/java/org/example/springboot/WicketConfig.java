package org.example.springboot;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@Configuration
public class WicketConfig {

    /**
     * Configura o filtro do Wicket para interceptar as requisições HTTP.
     */
    @Bean
    public ServletRegistrationBean<WicketFilter> wicketFilter() {
        ServletRegistrationBean<WicketFilter> servletRegistrationBean = new ServletRegistrationBean<>(
                new WicketFilter(), "/wicket/*");
        servletRegistrationBean.setName("wicketFilter");
        return servletRegistrationBean;
    }

    /**
     * Registro do Spring Component Injector que injeta as dependências do Spring nos componentes Wicket.
     */
    @Bean
    public WebApplication wicketApplication() {
        return new WebApplication() {
            @Override
            public Class<?> getHomePage() {
                return HomePage.class;  // Página inicial do Wicket
            }

            @Override
            public void init() {
                super.init();
                getComponentInstantiationListeners().add(new SpringComponentInjector(this));
                // Outros componentes de inicialização do Wicket podem ser configurados aqui.
            }
        };
    }

    /**
     * Configuração do filtro para o Spring Boot e Wicket (caso haja necessidade de filtrar a URL).
     */
    @Bean
    public FilterRegistrationBean<WicketFilter> wicketFilterRegistration() {
        FilterRegistrationBean<WicketFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WicketFilter());
        registrationBean.addUrlPatterns("/wicket/*"); // Configura o filtro para a URL '/wicket/*'
        return registrationBean;
    }
}
