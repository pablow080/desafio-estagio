package org.desafioestagio.backend;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.service.ClienteService;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.basic.ListItem;

import java.util.List;

public class ListarClientesPage extends WebPage {
    @SpringBean
    private ClienteService clienteService;

    public ListarClientesPage() {
        List<Cliente> clientes = clienteService.listarTodos();
        ListView<Cliente> listaClientes = new ListView<Cliente>("clientes", clientes) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                item.add(new Label("nome", item.getModel().getObject().getNome()));
                item.add(new Label("cpfCnpj", item.getModel().getObject().getCpfCnpj()));
            }
        };
        add(listaClientes);
    }
}
