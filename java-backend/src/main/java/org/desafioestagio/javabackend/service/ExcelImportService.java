package org.desafioestagio.javabackend.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;
import java.io.InputStream;
import java.util.*;

public class ExcelImportService {

    public List<Cliente> importarClientesDeExcel(InputStream fileInputStream) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);  // A primeira aba do Excel

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;  // Ignora a primeira linha (cabeçalho)

            Cliente cliente = new Cliente();
            cliente.setNome(row.getCell(0).getStringCellValue());

            // Converte a String para o enum TipoPessoa
            String tipoPessoaString = row.getCell(1).getStringCellValue();
            cliente.setTipoPessoa(TipoPessoa.fromString(tipoPessoaString));

            cliente.setCpfCnpj(row.getCell(2).getStringCellValue());
            cliente.setEmail(row.getCell(3).getStringCellValue());

            // Valide duplicatas e formato antes de adicionar
            if (validarCliente(cliente)) {
                clientes.add(cliente);
            }
        }

        workbook.close();
        return clientes;
    }


    private boolean validarCliente(Cliente cliente) {
        // Lógica de validação (duplicatas, formato, etc.)
        return true;  // Retorne true se válido
    }

    private String getStringCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";  // Se a célula estiver vazia, retorna uma string vazia
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return "";  // Retorna vazio para outros tipos de células
        }
    }

    private TipoPessoa getTipoPessoa(String tipoPessoaStr) {
        try {
            return TipoPessoa.valueOf(tipoPessoaStr.toUpperCase());  // Converte String para enum TipoPessoa
        } catch (IllegalArgumentException e) {
            // Caso o valor seja inválido para o enum, retornar um valor padrão ou lançar exceção
            return TipoPessoa.FISICA;  // Exemplo de retorno padrão
        }
    }
}
