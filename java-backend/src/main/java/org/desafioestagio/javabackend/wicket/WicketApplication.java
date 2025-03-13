package org.desafioestagio.javabackend.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.desafioestagio.javabackend.pages.HomePage;
import org.springframework.stereotype.Component;

@Component
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class; // Defina sua página inicial do Wicket aqui
    }
}
