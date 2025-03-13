package org.desafioestagio.javabackend.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    private static final Logger logger = LoggerFactory.getLogger(JasperReportService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] gerarRelatorioClientes() throws Exception {
        try {
            // Carregar lista de clientes do banco
            List<Cliente> clientes = clienteRepository.findAll();
            if (clientes.isEmpty()) {
                logger.warn("Nenhum cliente encontrado para gerar o relatório.");
                throw new Exception("Nenhum cliente encontrado para gerar o relatório.");
            }

            // Criar DataSource a partir da lista de clientes
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);

            // Carregar o template .jrxml
            Resource resource = resourceLoader.getResource("classpath:reports/ClienteReport.jrxml");
            try (InputStream reportStream = resource.getInputStream()) {
                JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

                // Parâmetros do relatório (caso precise passar mais parâmetros)
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("TITULO", "Relatório de Clientes");

                // Preencher relatório com os dados
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

                // Exportar para PDF e retornar o byte array
                return JasperExportManager.exportReportToPdf(jasperPrint);
            }
        } catch (JRException e) {
            logger.error("Erro ao gerar o relatório Jasper: ", e);
            throw new Exception("Erro ao gerar o relatório Jasper: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Erro inesperado ao gerar o relatório: ", e);
            throw new Exception("Erro inesperado ao gerar o relatório: " + e.getMessage(), e);
        }
    }
}
