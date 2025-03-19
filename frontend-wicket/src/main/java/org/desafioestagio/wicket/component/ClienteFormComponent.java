package org.desafioestagio.wicket.components;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.submit.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.desafioestagio.backend.model.Cliente;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.service.ClienteService;

public class ClienteFormComponent extends Panel {
    @SpringBean
    private ClienteService clienteService;

    public ClienteFormComponent(String id, Cliente cliente) {
        super(id);

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
