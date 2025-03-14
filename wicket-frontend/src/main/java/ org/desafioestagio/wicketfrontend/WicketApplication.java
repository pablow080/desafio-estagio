package org.desafioestagio.wicketfrontend;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebApplication;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.FileUploadField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.resource.ResourceStreamRequestHandler;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.desafioestagio.wicketfrontend.service.ExcelExportService;
import org.desafioestagio.wicketfrontend.service.ExcelImportService;
import org.desafioestagio.wicketfrontend.model.Cliente;
import org.desafioestagio.wicketfrontend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Component
public class WicketApplication extends WebApplication {

    @Autowired
    private ExcelExportService exportService; // Injetando o serviço de exportação

    @Autowired
    private ExcelImportService importService; // Injetando o serviço de importação

    @Autowired
    private ClienteService clienteService; // Injetando o serviço de cliente

    private File file;  // Campo para armazenar o arquivo carregado

    @Override
    public Class<? extends org.apache.wicket.Page> getHomePage() {
        return HomePage.class;  // Página inicial do Wicket
    }

    @Override
    protected void init() {
        super.init();
        // Injeção de dependências do Spring
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        // Feedback Panel para exibir mensagens de sucesso ou erro
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        // Botão para exportação para Excel
        add(new AjaxButton("exportarExcel") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                try {
                    List<Cliente> clientes = clienteService.listarClientes(); // Recuperando dados dos clientes
                    byte[] excelData = exportService.exportarClientesParaExcel(clientes); // Gerando dados para exportação
                    getRequestCycle().scheduleRequestHandlerAfterCurrent(
                            new ResourceStreamRequestHandler(new ByteArrayInputStream(excelData), "clientes.xlsx"));
                    info("Exportação para Excel concluída com sucesso!");
                } catch (Exception e) {
                    error("Erro ao exportar para Excel: " + e.getMessage());
                }
                target.add(feedbackPanel);  // Atualizando o FeedbackPanel com o status da operação
            }
        });

        // Formulário para importar dados de Excel
        Form<Void> uploadForm = new Form<>("uploadForm");
        FileUploadField fileUploadField = new FileUploadField("fileUpload", new PropertyModel<>(this, "file"));
        uploadForm.add(fileUploadField);
        uploadForm.add(new Button("importar") {
            @Override
            public void onSubmit() {
                try {
                    if (file != null) {
                        List<Cliente> clientesImportados = importService.importarClientesDeExcel(new FileInputStream(file)); // Importando os dados do arquivo Excel
                        // Salvando os clientes importados
                        for (Cliente cliente : clientesImportados) {
                            clienteService.salvar(cliente); // Persistindo no banco de dados
                        }
                        info("Clientes importados com sucesso!");
                    }
                } catch (Exception e) {
                    error("Erro ao importar clientes: " + e.getMessage());
                }
            }
        });
        add(uploadForm);  // Adicionando o formulário à página
    }
}
