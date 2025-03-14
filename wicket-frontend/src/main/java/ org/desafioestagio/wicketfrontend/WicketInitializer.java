package org.desafio.estagio.frontend;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Component
public class WicketInitializer implements ServletContextInitializer {

    // Injeção do ApplicationContext
    private final ApplicationContext applicationContext;

    public WicketInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Cria e configura o filtro do Wicket
        WicketFilter wicketFilter = new WicketFilter();
        wicketFilter.setFilterPath("/wicket/*");

        // Registra o filtro do Wicket no ServletContext
        servletContext.addFilter("wicket", wicketFilter)
                .addMappingForUrlPatterns(null, false, "/wicket/*");

        // Registra o Spring Component Injector para injeção de dependências
        wicketFilter.setApplicationContext(applicationContext);
    }
}
