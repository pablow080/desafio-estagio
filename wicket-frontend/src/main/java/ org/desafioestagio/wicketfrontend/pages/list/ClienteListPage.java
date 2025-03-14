package org.desafioestagio.wicketfrontend.pages.list;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.request.handler.RenderPageRequestHandler;
import org.desafioestagio.wicketfrontend.pages.HomePage;
import java.util.List;

public class ClienteListPage extends WebPage {

    private static final long serialVersionUID = 1L;
    private List<Cliente> clientes;

    public ClientesListPage() {
        clientes = List.of(
                new Cliente("João Silva", "joao@email.com"),
                new Cliente("Maria Souza", "maria@email.com")
        );

        add(new ListView<>("clientes", clientes) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                item.add(new Label("nome", new PropertyModel<>(item.getModel(), "nome")));
                item.add(new Label("email", new PropertyModel<>(item.getModel(), "email")));
            }
        });

        // Formulário apenas para conter os botões
        Form<?> form = new Form<>("form");
        add(form);

        // Botão AJAX para voltar à HomePage
        form.add(new AjaxButton("backToHome") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                setResponsePage(HomePage.class);
            }
        });
    }
}
