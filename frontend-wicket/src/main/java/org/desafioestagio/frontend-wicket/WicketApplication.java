package org.desafioestagio.frontend-wicket;

import org.apache.wicket.spring.boot.SpringBootWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WicketApplication extends SpringBootWebApplication {

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;  // Página inicial
    }

    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        mountPage("/home", HomePage.class);
        mountPage("/", HomePage.class); // Mapeando a página inicial para a raiz
    }

    public static void main(String[] args) {
        SpringApplication.run(WicketApplication.class, args);
    }
}
