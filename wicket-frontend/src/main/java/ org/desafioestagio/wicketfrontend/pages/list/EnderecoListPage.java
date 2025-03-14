package org.desafioestagio.wicketfrontend.pages.list;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AjaxLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.repeater.data.table.ListItem;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PageableListView;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.PropertyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.wicket.request.resource.ResourceResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import net.sf.jasperreports.engine.JRException;
import java.io.IOException;
import java.util.List;

public class EnderecoListPage extends WebPage {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JasperReportService jasperReportService;  // Injeção de dependência do serviço de relatórios

    @Autowired
    private EnderecoService enderecoService;  // Injeção do serviço de endereços

    private List<Endereco> enderecos;

    public EnderecoListPage() {
        // Carregar a lista de endereços
        enderecos = enderecoService.listarEnderecos();  // Substitua com seu método de busca de endereços

        // Criar o PageableListView para exibir a lista de endereços
        PageableListView<Endereco> listView = new PageableListView<Endereco>("enderecos", new PropertyModel<>(this, "enderecos")) {
            @Override
            protected void populateItem(ListItem<Endereco> item) {
                item.add(new Label("logradouro", new PropertyModel<>(item.getModel(), "logradouro")));
                item.add(new Label("bairro", new PropertyModel<>(item.getModel(), "bairro")));
                item.add(new Label("cidade", new PropertyModel<>(item.getModel(), "cidade")));
                item.add(new Label("estado", new PropertyModel<>(item.getModel(), "estado")));
                item.add(new Label("cep", new PropertyModel<>(item.getModel(), "cep")));

                // Link para gerar o relatório individual de cada endereço
                item.add(new AjaxLink<Void>("gerarRelatorio") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Endereco endereco = item.getModelObject();
                        gerarRelatorioIndividual(endereco);  // Chama o método para gerar o relatório
                    }
                });
            }
        };

        listView.setItemsPerPage(10);
        add(listView);

        // Adicionar navegação de páginas
        add(new PagingNavigator("navigator", listView));

        // Adicionar FeedbackPanel
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        // Link para gerar o relatório completo de todos os endereços
        add(new AjaxLink<Void>("gerarRelatorioCompleto") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                gerarRelatorioCompleto();
            }
        });
    }

    // Método para gerar o relatório individual de um endereço
    private void gerarRelatorioIndividual(Endereco endereco) {
        try {
            // Chama o serviço para gerar o relatório
            byte[] pdfBytes = jasperReportService.gerarRelatorioEndereco(endereco);  // Gera o PDF para o endereço

            // Envia o relatório gerado como resposta para o usuário
            ResourceResponse resourceResponse = new ResourceResponse();
            resourceResponse.setContentType("application/pdf");
            resourceResponse.setDisposition("attachment; filename=relatorio_endereco_" + endereco.getId() + ".pdf");

            resourceResponse.setData(pdfBytes);

            // Envia a resposta para o navegador do usuário
            RequestCycle.get().setResponse(resourceResponse);

        } catch (JRException | IOException e) {
            error("Erro ao gerar o relatório: " + e.getMessage());  // Exibe erro no FeedbackPanel
        }
    }

    // Método para gerar o relatório completo de todos os endereços
    private void gerarRelatorioCompleto() {
        try {
            // Chama o serviço para gerar o relatório completo com todos os endereços
            byte[] pdfRelatorio = jasperReportService.gerarRelatorioEnderecos();  // Gera o PDF completo

            // Envia o PDF gerado para download ou exibe o relatório
            ResourceResponse resourceResponse = new ResourceResponse();
            resourceResponse.setContentType("application/pdf");
            resourceResponse.setDisposition("attachment; filename=relatorio_completo_enderecos.pdf");
            resourceResponse.setData(pdfRelatorio);

            // Envia a resposta para o navegador do usuário
            RequestCycle.get().setResponse(resourceResponse);

        } catch (JRException | IOException e) {
            error("Erro ao gerar o relatório completo: " + e.getMessage());
        }
    }
}
