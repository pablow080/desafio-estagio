package org.desafioestagio.backend.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.desafioestagio.backend.model.Cliente;

import java.util.List;

public class ClientesListComponent extends Panel {
    public ClientesListComponent(String id, List<Cliente> clientes) {
        super(id);

        ListView<Cliente> listView = new ListView<Cliente>("clientes", clientes) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                item.add(new Label("nome", item.getModel().getObject().getNome()));
                item.add(new Label("cpfCnpj", item.getModel().getObject().getCpfCnpj()));
            }
        };

        add(listView);
    }
}
