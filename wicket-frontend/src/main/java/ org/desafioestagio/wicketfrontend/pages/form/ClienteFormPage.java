package org.desafioestagio.wicketfrontend.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.RegexValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;

public class ClienteFormPage extends WebPage {
    public ClienteFormPage() {
        // Painel de feedback
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        // Formulário para cadastro de cliente
        Form<Cliente> form = new Form<Cliente>("form") {
            @Override
            protected void onSubmit() {
                // Lógica para salvar ou validar o cliente
                Cliente cliente = getModelObject();
                success("Cliente cadastrado com sucesso!");
            }
        };
        add(form);

        // Campo CPF
        TextField<String> cpfField = new TextField<>("cpf", new Model<>());
        cpfField.setRequired(true);
        cpfField.add(new RegexValidator("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
        form.add(cpfField);

        // Campo CNPJ
        TextField<String> cnpjField = new TextField<>("cnpj", new Model<>());
        cnpjField.setRequired(true);
        cnpjField.add(new RegexValidator("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"));
        form.add(cnpjField);

        // Campo CEP
        TextField<String> cepField = new TextField<>("cep", new Model<>());
        cepField.setRequired(true);
        cepField.add(new RegexValidator("\\d{5}-\\d{3}"));
        form.add(cepField);

        // Tipo de Pessoa: Físico ou Jurídico
        IModel<String> tipoPessoaModel = Model.of("fisica");
        RadioGroup<String> tipoPessoa = new RadioGroup<>("tipoPessoa", tipoPessoaModel);
        tipoPessoa.add(new Radio<>("fisica", Model.of("fisica")));
        tipoPessoa.add(new Radio<>("juridica", Model.of("juridica")));

        // Campos de CPF/CNPJ
        TextField<String> campoCPF = new TextField<>("cpf", Model.of(""));
        TextField<String> campoCNPJ = new TextField<>("cnpj", Model.of(""));
        campoCPF.setOutputMarkupId(true);
        campoCNPJ.setOutputMarkupId(true);
        campoCNPJ.setVisible(false); // Inicialmente oculto

        tipoPessoa.add(new AjaxFormComponentUpdatingBehavior("onclick") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                boolean isPessoaFisica = "fisica".equals(tipoPessoaModel.getObject());
                campoCPF.setVisible(isPessoaFisica);
                campoCNPJ.setVisible(!isPessoaFisica);
                target.add(campoCPF, campoCNPJ);
            }
        });

        form.add(tipoPessoa, campoCPF, campoCNPJ);
    }
}
