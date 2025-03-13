package org.desafioestagio.javabackend.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.desafioestagio.javabackend.model.Cliente;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportService {

    // Método para formatar CPF/CNPJ
    private String formatarCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 11) {
            // Formatar como CPF: xxx.xxx.xxx-xx
            return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } else if (cpfCnpj.length() == 14) {
            // Formatar como CNPJ: xx.xxx.xxx/xxxx-xx
            return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return cpfCnpj;
    }

    public byte[] exportarClientesParaExcel(List<Cliente> clientes) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        // Criação do cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome");
        headerRow.createCell(1).setCellValue("Tipo Pessoa");
        headerRow.createCell(2).setCellValue("CPF/CNPJ");
        headerRow.createCell(3).setCellValue("Email");

        // Formatação para o cabeçalho (negrito)
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // Preenchendo os dados
        int rowNum = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cliente.getNome());
            row.createCell(1).setCellValue(cliente.getTipoPessoa().name());
            row.createCell(2).setCellValue(formatarCpfCnpj(cliente.getCpfCnpj()));
            row.createCell(3).setCellValue(cliente.getEmail());
        }

        // Ajuste automático das colunas
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrevendo no arquivo de bytes
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new IOException("Erro ao escrever o arquivo Excel", e);
        } finally {
            workbook.close();
        }
    }
}
