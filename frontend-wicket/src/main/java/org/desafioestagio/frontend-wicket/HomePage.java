package org.desafioestagio.frontend;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

    public HomePage() {
        // Criando um botão para ir para a página de cadastro
        Button cadastrarButton = new Button("cadastrarButton", new Model<>("Ir para Cadastro")) {
            @Override
            public void onSubmit() {
                setResponsePage(CadastrarPage.class); // Navegar para a página de cadastro
            }
        };
        add(cadastrarButton);

        // Criando um botão para ir para a lista de clientes
        Button clienteButton = new Button("clienteButton", new Model<>("Lista de Clientes")) {
            @Override
            public void onSubmit() {
                setResponsePage(ClientePage.class); // Navegar para a página de lista de clientes
            }
        };
        add(clienteButton);

        // Criando um botão para ir para a lista de endereços
        Button enderecoButton = new Button("enderecoButton", new Model<>("Lista de Endereços")) {
            @Override
            public void onSubmit() {
                setResponsePage(EnderecoPage.class); // Navegar para a página de lista de endereços
            }
        };
        add(enderecoButton);
    }
}
