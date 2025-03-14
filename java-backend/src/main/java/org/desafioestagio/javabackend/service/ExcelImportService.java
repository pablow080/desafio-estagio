package org.desafioestagio.javabackend.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportService {

    public List<Cliente> importarClientesDeExcel(InputStream fileInputStream) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);  // Pega a primeira aba do Excel

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;  // Ignora a primeira linha (cabeçalho)

                Cliente cliente = new Cliente();
                cliente.setNome(getStringCellValue(row, 0));
                cliente.setTipoPessoa(TipoPessoa.fromString(getStringCellValue(row, 1)));
                cliente.setCpfCnpj(getStringCellValue(row, 2));
                cliente.setEmail(getStringCellValue(row, 3));

                if (validarCliente(cliente)) {
                    clientes.add(cliente);
                } else {
                    // Log ou exceção se necessário (cliente inválido)
                    System.out.println("Cliente inválido: " + cliente);
                }
            }
        } catch (Exception e) {
            // Log para erros na leitura do Excel
            throw new Exception("Erro ao ler o arquivo Excel: " + e.getMessage(), e);
        }

        return clientes;
    }

    private boolean validarCliente(Cliente cliente) {
        // Validações de CPF/CNPJ e Email podem ser implementadas aqui
        return cliente.getNome() != null && !cliente.getNome().isEmpty()
                && cliente.getCpfCnpj() != null && !cliente.getCpfCnpj().isEmpty()
                && cliente.getEmail() != null && !cliente.getEmail().isEmpty();
    }

    private String getStringCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            default -> "";
        };
    }
}
