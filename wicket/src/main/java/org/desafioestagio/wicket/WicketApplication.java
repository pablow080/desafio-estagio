package org.desafioestagio.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.desafioestagio.wicket.pages.IndexPage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return IndexPage.class;
    }
}
