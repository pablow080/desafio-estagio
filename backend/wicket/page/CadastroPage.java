package org.desafioestagio.wicket.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;

public class CadastroPage extends WebPage {

    public CadastroPage() {
        // Criando o formulário de cadastro
        Form<Cliente> form = new Form<>("form");

        // Criando modelo para o cliente
        Cliente cliente = new Cliente();
        form.setModel(new Model<>(cliente));

        // Campo para tipo de pessoa (Física ou Jurídica)
        DropDownChoice<TipoPessoa> tipoPessoaChoice = new DropDownChoice<>("tipoPessoa",
                new Model<>(cliente.getTipoPessoa()),
                Arrays.asList(TipoPessoa.FISICA, TipoPessoa.JURIDICA)
        );
        tipoPessoaChoice.setRequired(true);
        form.add(tipoPessoaChoice);

        // Campo para o nome
        TextField<String> nomeField = new TextField<>("nome", new Model<>(""));
        nomeField.setRequired(true);
        nomeField.add(StringValidator.lengthBetween(3, 100));  // Validação de tamanho
        form.add(nomeField);

        // Campo para CPF/CNPJ
        TextField<String> cpfCnpjField = new TextField<>("cpfCnpj", new Model<>(""));
        cpfCnpjField.setRequired(true);
        cpfCnpjField.add(new PatternValidator("\\d{11}|\\d{14}"));  // Validação para CPF ou CNPJ
        form.add(cpfCnpjField);

        // Campo para RG (apenas para pessoa física)
        TextField<String> rgField = new TextField<>("rg", new Model<>(""));
        rgField.setRequired(true);
        rgField.add(new PatternValidator("\\d{7,12}"));
        rgField.setOutputMarkupId(true); // Necessário para manipulação via AJAX
        form.add(rgField);

        // Campo para data de nascimento (apenas para pessoa física)
        TextField<String> dataNascimentoField = new TextField<>("dataNascimento", new Model<>(""));
        dataNascimentoField.setRequired(true);
        form.add(dataNascimentoField);

        // Campo para Razão Social (apenas para pessoa jurídica)
        TextField<String> razaoSocialField = new TextField<>("razaoSocial", new Model<>(""));
        razaoSocialField.setRequired(true);
        form.add(razaoSocialField);

        // Campo para Inscrição Estadual (apenas para pessoa jurídica)
        TextField<String> inscricaoEstadualField = new TextField<>("inscricaoEstadual", new Model<>(""));
        inscricaoEstadualField.setRequired(true);
        inscricaoEstadualField.add(new PatternValidator("\\d{9,14}"));
        form.add(inscricaoEstadualField);

        // Campo para e-mail
        EmailTextField emailField = new EmailTextField("email", new Model<>(""));
        emailField.setRequired(true);
        form.add(emailField);

        // Botão de envio do formulário
        Button submitButton = new Button("submit") {
            @Override
            public void onSubmit() {
                // Aqui você pode implementar a lógica para salvar os dados no banco
                Cliente cliente = form.getModelObject();

                // Lógica de validação dependendo do tipo de pessoa
                if (cliente.getTipoPessoa() == TipoPessoa.FISICA) {
                    // Validar campos específicos para pessoa física
                    if (cliente.getRg() == null || cliente.getRg().isEmpty()) {
                        // Exemplo de validação manual para RG
                        error("RG é obrigatório para pessoa física.");
                    }
                } else {
                    // Validar campos específicos para pessoa jurídica
                    if (cliente.getRazaoSocial() == null || cliente.getRazaoSocial().isEmpty()) {
                        // Exemplo de validação manual para Razão Social
                        error("Razão Social é obrigatória para pessoa jurídica.");
                    }
                }

                // Redireciona para a página inicial após o cadastro
                setResponsePage(HomePage.class);
            }
        };
        form.add(submitButton);

        // Adiciona o formulário à página
        add(form);
    }
}
