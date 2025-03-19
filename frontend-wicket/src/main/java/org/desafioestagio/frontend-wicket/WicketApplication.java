package org.desafioestagio.backend;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.MarkupSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WicketApplication extends WebApplication {

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class; // Substitua HomePage pela sua classe de página inicial
    }

    @Override
    public void init() {
        super.init();
        // Configurações do Wicket, como caches, sessões, etc.
        getMarkupSettings().setStripWicketTags(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(WicketApplication.class, args);
    }
}
