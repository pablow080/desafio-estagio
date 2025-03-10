package br.com.unika.wicketfrontend;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.SpringWebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.context.AnnotationConfigWebApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class WicketFrontendApplication extends SpringWebApplication {

    @Override
    public void init() {
        super.init();
        // Configuração do Wicket
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Class<?> getHomePage() {
        return HomePage.class; // Se você tiver uma página específica do Wicket
    }
}
