package org.desafioestagio.javabackend.relatorio;

import net.sf.jasperreports.engine.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeradorRelatorio {
    public static void main(String[] args) throws JRException {
        // Caminho do arquivo JRXML ou compilado.jasper
        String caminhoRelatorio = "src/main/resources/reports/relatorio-clientes.jrxml";

        // Criar um mapa de parâmetros
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tituloRelatorio", "Relatório de Clientes");

        // Preencher o relatório sem conexão com banco (exemplo)
        JasperPrint print = JasperFillManager.fillReport(caminhoRelatorio, parametros, new JREmptyDataSource());

        // Nome do arquivo PDF com data e hora
        String nomeArquivo = "relatorio-clientes-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Exportar para PDF
        JasperExportManager.exportReportToPdfFile(print, nomeArquivo);

        System.out.println("Relatório gerado com sucesso: " + nomeArquivo);
    }
}
