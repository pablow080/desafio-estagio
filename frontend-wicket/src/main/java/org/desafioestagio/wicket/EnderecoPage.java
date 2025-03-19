package org.desafioestagio.backend.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.service.EnderecoService;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;

public class EnderecoPage extends WebPage {

    @SpringBean
    private EnderecoService enderecoService;

    public EnderecoPage() {
        // Título da página
        add(new Label("title", "Gestão de Endereços"));

        // Listar endereços
        List<Endereco> enderecos = enderecoService.listarTodos();
        ListView<Endereco> enderecoListView = new ListView<Endereco>("enderecoList", enderecos) {
            @Override
            protected void populateItem(ListItem<Endereco> item) {
                item.add(new Label("logradouro", item.getModel().getObject().getLogradouro()));
                item.add(new Button("deleteButton") {
                    @Override
                    public void onSubmit() {
                        Endereco endereco = item.getModel().getObject();
                        enderecoService.excluir(endereco.getId());
                        setResponsePage(EnderecoPage.class); // Recarrega a página após excluir
                    }
                });
            }
        };
        add(enderecoListView);

        // Formulário para adicionar novo endereço
        Form<Endereco> addForm = new Form<Endereco>("addForm") {
            @Override
            protected void onSubmit() {
                Endereco endereco = new Endereco();
                // Defina os dados do endereço (pode criar campos de input aqui)
                endereco.setLogradouro("Rua Exemplo");
                enderecoService.salvar(endereco);
                setResponsePage(EnderecoPage.class); // Recarrega a página após adicionar
            }
        };
        add(addForm);

        add(new Button("addButton", new Model<>("Adicionar Endereço")));
    }
}
