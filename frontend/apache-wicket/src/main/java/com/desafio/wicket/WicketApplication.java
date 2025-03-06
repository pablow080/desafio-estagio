package com.example.desafio.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import com.example.desafio.wicket.pages.ClientesPage;

public class WicketApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return ClientesPage.class;
    }

    @Override
    protected void init() {
        super.init();

        // Configuração de mounts
        mountPage("/clientes", ClientesPage.class);

        // Configuração de recursos estáticos
        getResourceSettings().getResourceFinders()
                .add(new WebApplicationPath(getServletContext(), "assets"));

        // Configuração de exceções
        getApplicationSettings().setPageExpiredErrorPage(ClientesPage.class);
    }
}