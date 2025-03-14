package org.desafioestagio.wicketfrontend.pages.relatorio;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.file.File;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import java.io.FileInputStream;
import java.io.IOException;

public class RelatorioPage extends WebPage {

    private String clienteRelatorioUrl;

    public RelatorioPage() {
        // Defina a URL do relatório gerado ou caminho do arquivo
        this.clienteRelatorioUrl = "/relatorios/cliente/relatorioCliente.pdf";  // Caminho do relatório gerado

        // Adiciona um painel de feedback para exibir mensagens de erro ou sucesso
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        // Link para fazer o download do relatório
        add(new Link<Void>("downloadRelatorio") {
            @Override
            public void onClick() {
                // Redireciona para o download do arquivo gerado
                PageParameters params = new PageParameters();
                params.add("file", clienteRelatorioUrl);
                setResponsePage(new FileDownloadPage(params));  // Redireciona para a página que fará o download
            }
        });
    }

    // Página que processa o download do arquivo
    public static class FileDownloadPage extends WebPage {

        public FileDownloadPage(PageParameters parameters) {
            // Pega o caminho do arquivo a partir dos parâmetros
            String filePath = parameters.get("file").toString("");

            // Tenta localizar o arquivo
            File file = new File(filePath);

            if (file.exists() && file.isFile()) {
                // Cria um stream de arquivo
                IResourceStream resourceStream = new FileResourceStream(file);

                // Configura o handler de download do arquivo
                ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(resourceStream, "relatorioCliente.pdf");
                handler.setContentDispositionAttachment(true);  // Faz o arquivo ser tratado como anexo

                // Envia a resposta para o download
                getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
            } else {
                // Se o arquivo não for encontrado, exibe uma mensagem de erro
                error("Arquivo não encontrado.");
            }
        }
    }
}
