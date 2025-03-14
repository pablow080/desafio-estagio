package org.desafioestagio.javabackend.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioService {

    public void gerarRelatorio() {
        try {
            // Exemplo de lista de clientes
            List<Cliente> clientes = obterClientes();

            // Carregar o template JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/relatorio-clientes.xlsx");

            // Configuração de parâmetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tituloRelatorio", "Relatório de Clientes");

            // Usando JRBeanCollectionDataSource para passar a lista de objetos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);

            // Preencher o relatório com os dados
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Exportar o relatório para PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio_clientes.pdf");

            System.out.println("Relatório gerado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Exemplo de método para obter lista de clientes
    private List<Cliente> obterClientes() {
        List<Cliente> clientes = new ArrayList<>();

        // Criando clientes com todos os campos obrigatórios
        TipoPessoa tipoPessoa = TipoPessoa.FISICA; // Ou o tipo de pessoa adequado
        clientes.add(new Cliente(tipoPessoa, "12345678901", "John Doe", "john.doe@example.com"));
        clientes.add(new Cliente(tipoPessoa, "98765432100", "Jane Smith", "jane.smith@example.com"));

        return clientes;
    }
}
