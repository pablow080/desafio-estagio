package org.desafioestagio.javabackend.controller;

import org.desafioestagio.javabackend.service.JasperReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);

    @Autowired
    private JasperReportService jasperReportService;

    @GetMapping("/clientes")
    public ResponseEntity<byte[]> gerarRelatorioClientes() {
        try {
            byte[] relatorio = jasperReportService.gerarRelatorioClientes();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_clientes.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(relatorio);
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório de clientes", e);  // Logando o erro
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
