package org.desafioestagio.javabackend.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelExportServiceTest {

    @InjectMocks
    private ExcelExportService exportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportarClientesParaExcel() throws Exception {
        // Criar lista de clientes com dados completos
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpfCnpj("12345678901");
        cliente.setTipoPessoa(TipoPessoa.FISICA);

        List<Cliente> clientes = List.of(cliente);

        // Simular o método real
        byte[] excelData = exportService.exportarClientesParaExcel(clientes);

        // Verificar resultados
        assertNotNull(excelData, "Os dados exportados não podem ser nulos");
        assertTrue(excelData.length > 0, "Os dados exportados não podem ser vazios");

        // Usando Apache POI para verificar o conteúdo do Excel
        try (ByteArrayInputStream bis = new ByteArrayInputStream(excelData)) {
            Workbook workbook = new XSSFWorkbook(bis); // Usando Apache POI para ler o arquivo Excel
            Sheet sheet = workbook.getSheetAt(0); // Pegando a primeira planilha

            // Verificar se o cabeçalho está correto
            Row headerRow = sheet.getRow(0); // Primeira linha, geralmente o cabeçalho
            assertNotNull(headerRow, "A linha de cabeçalho não pode ser nula");
            assertEquals("Nome", headerRow.getCell(0).getStringCellValue(), "Cabeçalho da coluna 'Nome' incorreto");
            assertEquals("CPF/CNPJ", headerRow.getCell(1).getStringCellValue(), "Cabeçalho da coluna 'CPF/CNPJ' incorreto");

            // Verificar se a primeira linha contém os dados esperados
            Row firstRow = sheet.getRow(1); // A primeira linha de dados (não cabeçalho)
            assertNotNull(firstRow, "A primeira linha de dados não pode ser nula");

            // Verificar se o nome do cliente está na primeira célula
            Cell nomeCell = firstRow.getCell(0); // Supondo que o nome esteja na primeira coluna (índice 0)
            assertNotNull(nomeCell, "O nome do cliente não pode ser nulo");
            assertEquals("John Doe", nomeCell.getStringCellValue(), "O nome do cliente está incorreto");

            // Verificar se o CPF do cliente está na segunda célula
            Cell cpfCell = firstRow.getCell(1); // Supondo que o CPF esteja na segunda coluna (índice 1)
            assertNotNull(cpfCell, "O CPF do cliente não pode ser nulo");
            assertEquals("12345678901", cpfCell.getStringCellValue(), "O CPF do cliente está incorreto");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao ler o arquivo Excel gerado", e);
        }
    }

}
