package org.desafioestagio.javabackend.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class HomePage extends WebPage {

    public HomePage() {
        add(new Link<Void>("clientesButton") {
            @Override
            public void onClick() {
                setResponsePage(ClientesPage.class);
            }
        });

        add(new Link<Void>("enderecosButton") {
            @Override
            public void onClick() {
                setResponsePage(EnderecosPage.class);
            }
        });

        add(new Link<Void>("relatorioButton") {
            @Override
            public void onClick() {
                setResponsePage(RelatorioPage.class);
            }
        });
    }
}
