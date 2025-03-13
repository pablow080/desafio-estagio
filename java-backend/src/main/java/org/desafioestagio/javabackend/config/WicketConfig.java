package org.desafioestagio.javabackend.config;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.desafioestagio.javabackend.pages.HomePage;
import org.springframework.stereotype.Component;

@Component
public class WicketConfig extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class; // Página inicial
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this)); // Integração com o Spring
    }
}
