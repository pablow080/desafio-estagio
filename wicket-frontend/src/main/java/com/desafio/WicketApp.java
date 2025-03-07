package com.desafio;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import com.desafio.pages.HomePage;
import com.desafio.pages.ErrorPage;
import org.apache.wicket.util.time.Duration;

public class WicketApp extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;  // Define a página inicial
    }

    @Override
    public void init() {
        super.init();

        // Monta URL amigável para a HomePage
        mountPage("/home", HomePage.class);

        // Configurações adicionais podem ir aqui
        getApplicationSettings().setInternalErrorPage(ErrorPage.class);
        getApplicationSettings().setPageExpiredErrorPage(ErrorPage.class);

        // Exemplo de configuração para remover cache
        getResourceSettings().setResourcePollFrequency(Duration.seconds(1));

        // Definindo o page mapper corretamente para Wicket 7
        getPageSettings().setVersionedPages(false);  // Pode desabilitar cache de páginas
    }
}
