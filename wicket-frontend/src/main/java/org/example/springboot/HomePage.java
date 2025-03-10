package org.example.springboot;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends WebPage {
    public HomePage() {
        add(new Label("message", "Bem-vindo ao Apache Wicket com Spring Boot!"));
    }
}