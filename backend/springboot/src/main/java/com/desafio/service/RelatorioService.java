package com.desafio.service;

import com.desafio.model.ClienteModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class RelatorioService {

    public void exportarRelatorioClientesPDF(List<Cliente> clientes, HttpServletResponse response)
            throws IOException, JRException {

        File file = ResourceUtils.getFile("classpath:reports/clientes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=clientes.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}