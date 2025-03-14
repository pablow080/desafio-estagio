package org.desafioestagio.wicketfrontend.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.Model;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.StringValidator;
import org.desafioestagio.wicketfrontend.model.Endereco;
import org.desafioestagio.wicketfrontend.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoFormPage extends WebPage {

    @Autowired
    private EnderecoService enderecoService;

    private Endereco endereco;

    public EnderecoFormPage() {
        endereco = new Endereco(); // Criar um novo objeto de Endereco

        // Feedback panel para mensagens de erro ou sucesso
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        // Formulário de cadastro de Endereço
        Form<Endereco> form = new Form<Endereco>("form") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                try {
                    // Salvar o endereço no banco de dados
                    enderecoService.salvar(endereco);
                    info("Endereço cadastrado com sucesso!");
                } catch (Exception e) {
                    error("Erro ao cadastrar o endereço: " + e.getMessage());
                }
            }
        };

        // Campo Logradouro
        form.add(new TextField<String>("logradouro", new Model<>(endereco.getLogradouro()))
                .setRequired(true)
                .add(StringValidator.lengthBetween(5, 100)) // Validação de tamanho
        );

        // Campo Número
        form.add(new TextField<String>("numero", new Model<>(endereco.getNumero()))
                .setRequired(true)
        );

        // Campo Bairro
        form.add(new TextField<String>("bairro", new Model<>(endereco.getBairro()))
                .setRequired(true)
                .add(StringValidator.lengthBetween(3, 50))
        );

        // Campo Cidade
        form.add(new TextField<String>("cidade", new Model<>(endereco.getCidade()))
                .setRequired(true)
                .add(StringValidator.lengthBetween(3, 50))
        );

        // Campo Estado
        form.add(new TextField<String>("estado", new Model<>(endereco.getEstado()))
                .setRequired(true)
                .add(StringValidator.lengthBetween(2, 50))
        );

        // Campo CEP
        form.add(new TextField<String>("cep", new Model<>(endereco.getCep()))
                .setRequired(true)
                .add(StringValidator.lengthBetween(8, 9)) // Exemplo de CEP: "12345-678"
        );

        // Botão de envio
        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                enderecoService.salvar(endereco);
                setResponsePage(EnderecoListPage.class); // Redireciona para a página de listagem de Endereços
            }
        });

        add(form);
    }
}
