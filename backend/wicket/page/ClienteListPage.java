import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.Model;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.service.ClienteService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

import java.util.List;

public class ClientelistPage extends WebPage {

    @SpringBean
    private ClienteService clienteService;

    public ClientelistPage() {
        // Listar todos os clientes
        List<Cliente> clientes = clienteService.listarTodos(Pageable.unpaged()).getContent();

        // Tabela de clientes
        ListView<Cliente> clienteListView = new ListView<Cliente>("clienteList", clientes) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                Cliente cliente = item.getModelObject();

                // Adiciona o nome do cliente à coluna
                item.add(new Label("nome", cliente.getNome()));

                // Exibe o CPF ou CNPJ formatado
                item.add(new Label("cpfCnpj", cliente.getDocumentoFormatado()));

                // Adiciona botão de edição ou excluir
                item.add(new Button("editButton") {
                    @Override
                    public void onSubmit() {
                        // Lógica para editar cliente
                    }
                });

                item.add(new Button("deleteButton") {
                    @Override
                    public void onSubmit() {
                        // Lógica para excluir cliente
                    }
                });
            }
        };
        add(clienteListView);
    }
}
