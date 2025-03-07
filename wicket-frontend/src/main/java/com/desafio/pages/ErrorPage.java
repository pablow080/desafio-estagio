package com.desafio.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class ErrorPage extends WebPage {
    public ErrorPage() {
        // Adicionando um Label para exibir a mensagem de erro
        add(new Label("errorMessage", "Ocorreu um erro inesperado!"));
    }
}
