package org.desafioestagio.backend.config;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WicketConfig {

    @Bean
    public FilterRegistrationBean<WicketFilter> wicketFilter(WebApplication wicketApplication) {
        WicketFilter filter = new WicketFilter(wicketApplication);

        FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("wicket-filter");
        return registration;
    }

    //@Bean
    //public WebApplication wicketApplication() {
       // return new WicketApplication();
   // }
}