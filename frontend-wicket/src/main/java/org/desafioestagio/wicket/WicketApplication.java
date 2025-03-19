package org.desafioestagio.wicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

public class WicketApplication extends WebApplication {
    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        // Adicione configurações extras, se necessário
    }
}
