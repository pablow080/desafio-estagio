package org.desafioestagio.backend.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.service.ClienteService;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;

public class ClientePage extends WebPage {

    @SpringBean
    private ClienteService clienteService;

    public ClientePage() {
        // Título da página
        add(new Label("title", "Gestão de Clientes"));

        // Listar clientes
        List<Cliente> clientes = clienteService.listarTodos();
        ListView<Cliente> clienteListView = new ListView<Cliente>("clienteList", clientes) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                item.add(new Label("nome", item.getModel().getObject().getNome()));
                item.add(new Button("deleteButton") {
                    @Override
                    public void onSubmit() {
                        Cliente cliente = item.getModel().getObject();
                        clienteService.excluir(cliente.getId());
                        setResponsePage(ClientePage.class); // Recarrega a página após excluir
                    }
                });
            }
        };
        add(clienteListView);

        // Formulário para adicionar novo cliente
        Form<Cliente> addForm = new Form<Cliente>("addForm") {
            @Override
            protected void onSubmit() {
                Cliente cliente = new Cliente();
                // Defina os dados do cliente (pode criar campos de input aqui)
                cliente.setNome("Novo Cliente");
                clienteService.salvar(cliente);
                setResponsePage(ClientePage.class); // Recarrega a página após adicionar
            }
        };
        add(addForm);

        add(new Button("addButton", new Model<>("Adicionar Cliente")));
    }
}
