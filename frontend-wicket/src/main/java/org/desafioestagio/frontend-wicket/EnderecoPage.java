package org.desafioestagio.frontend-wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.service.EnderecoService;

import java.util.List;

public class EnderecoPage extends WebPage {

    @SpringBean
    private EnderecoService enderecoService;

    public EnderecoPage() {
        // Lista de Endereços
        List<Endereco> enderecos = enderecoService.listarTodos();
        ListView<Endereco> enderecoListView = new ListView<Endereco>("enderecoList", enderecos) {
            @Override
            protected void populateItem(ListItem<Endereco> item) {
                item.add(new Label("rua", item.getModel().getObject().getRua()));
                item.add(new Button("deleteButton") {
                    @Override
                    public void onSubmit() {
                        Endereco endereco = item.getModel().getObject();
                        enderecoService.excluir(endereco.getId());
                        setResponsePage(EnderecoPage.class); // Recarrega a página
                    }
                });
            }
        };
        add(enderecoListView);

        // Formulário de Busca por ID
        Form<Void> searchForm = new Form<>("searchForm");
        TextField<String> searchField = new TextField<>("searchField", new Model<>(""));
        searchForm.add(searchField);
        searchForm.add(new Button("searchButton") {
            @Override
            public void onSubmit() {
                String id = searchField.getModelObject();
                Endereco endereco = enderecoService.buscarPorId(id);
                if (endereco != null) {
                    // Exibir endereço encontrado ou redirecionar
                } else {
                    // Exibir mensagem de não encontrado
                }
            }
        });
        add(searchForm);
    }
}
