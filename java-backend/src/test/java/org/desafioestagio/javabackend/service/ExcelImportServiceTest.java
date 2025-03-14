package org.desafioestagio.javabackend.service;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.desafioestagio.javabackend.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelImportServiceTest {

    @InjectMocks
    private ExcelImportService importService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testImportarClientesDeExcel() throws Exception {
        // Criar um arquivo Excel simulado com dados fictícios usando Apache POI
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        // Adicionar cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome");
        headerRow.createCell(1).setCellValue("CPF/CNPJ");

        // Adicionar dados
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("John Doe");
        row1.createCell(1).setCellValue("12345678901");

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Jane Doe");
        row2.createCell(1).setCellValue("10987654321");

        // Convertendo o workbook em um array de bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] excelData = bos.toByteArray();
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(excelData);

        // Chama o método real
        List<Cliente> clientesImportados = importService.importarClientesDeExcel(fileInputStream);

        // Verifica se a lista de clientes não está vazia
        assertNotNull(clientesImportados, "A lista de clientes importados não pode ser nula");
        assertFalse(clientesImportados.isEmpty(), "A lista de clientes importados não pode ser vazia");

        // Verifique as propriedades dos primeiros clientes importados
        Cliente primeiroCliente = clientesImportados.get(0);
        assertNotNull(primeiroCliente.getNome(), "O nome do cliente não pode ser nulo");
        assertNotNull(primeiroCliente.getCpfCnpj(), "O CPF/CNPJ do cliente não pode ser nulo");

        // Validação de dados do primeiro cliente
        assertEquals("John Doe", primeiroCliente.getNome(), "O nome do primeiro cliente está incorreto");
        assertEquals("12345678901", primeiroCliente.getCpfCnpj(), "O CPF/CNPJ do primeiro cliente está incorreto");

        // Validação de dados do segundo cliente
        Cliente segundoCliente = clientesImportados.get(1);
        assertEquals("Jane Doe", segundoCliente.getNome(), "O nome do segundo cliente está incorreto");
        assertEquals("10987654321", segundoCliente.getCpfCnpj(), "O CPF/CNPJ do segundo cliente está incorreto");
    }
}
