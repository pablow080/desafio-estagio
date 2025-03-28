package org.desafioestagio.backend.config;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
package org.desafioestagio.wicket;


@Configuration
@Order(1)
public class WicketConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WicketApplication.class);  // Inicia WicketApplication
    }

    @Bean
    public FilterRegistrationBean<WicketFilter> wicketFilter() {
        FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new WicketFilter());
        registration.addUrlPatterns("/wicket/*");  // Filtra para URLs do Wicket
        registration.addInitParameter("applicationClassName", "org.desafioestagio.WicketApplication");
        registration.setName("WicketFilter");
        registration.setOrder(1);
        return registration;
    }
}
