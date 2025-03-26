package org.desafioestagio.wicket.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class IndexPage extends WebPage {

    public IndexPage() {
        // Adiciona um rótulo simples à página
        add(new Label("message", "Bem-vindo à página inicial!"));
    }
}