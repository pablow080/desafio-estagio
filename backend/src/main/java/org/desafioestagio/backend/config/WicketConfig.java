package org.desafioestagio.backend.config;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WicketServlet;
import org.apache.wicket.spring.SpringWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WicketConfig {

    @Bean
    public ServletRegistrationBean<WicketServlet> wicketServlet() {
        ServletRegistrationBean<WicketServlet> servlet = new ServletRegistrationBean<>(new WicketServlet(), "/wicket/*");
        servlet.setLoadOnStartup(1);
        return servlet;
    }

    @Bean
    public FilterRegistrationBean<WicketFilter> wicketFilter() {
        FilterRegistrationBean<WicketFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new WicketFilter());
        filter.addUrlPatterns("/wicket/*");
        return filter;
    }

    @Bean
    public <SpringWebApplication> SpringWebApplication springWebApplication() {
        return new SpringWebApplication() {
            @Override
            public Class<? extends WebPage> getHomePage() {
                return IndexPage.class;
            }
        };
    }
}
