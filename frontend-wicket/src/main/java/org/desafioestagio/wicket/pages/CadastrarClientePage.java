package org.desafioestagio.wicket.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.submit.Button;
import org.apache.wicket.model.PropertyModel;
import org.desafioestagio.backend.model.Cliente;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.service.ClienteService;

public class CadastrarClientePage extends WebPage {
    @SpringBean
    private ClienteService clienteService;

    public CadastrarClientePage() {
        Cliente cliente = new Cliente();  // Cliente a ser criado/alterado

        Form<Cliente> form = new Form<Cliente>("form", new PropertyModel<>(cliente, "nome")) {
            @Override
            protected void onSubmit() {
                clienteService.salvar(cliente);
                setResponsePage(ListarClientesPage.class);  // Após salvar, redireciona para a lista
            }
        };

        form.add(new TextField<String>("nome", new PropertyModel<>(cliente, "nome")));
        form.add(new TextField<String>("cpfCnpj", new PropertyModel<>(cliente, "cpfCnpj")));
        form.add(new Button("salvar"));

        add(form);
    }
}
