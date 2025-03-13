package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExcelExportServiceTest {

    @InjectMocks
    private ExcelExportService exportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportarClientesParaExcel() throws Exception {
        // Criar lista de clientes (com construtor padrão e ‘setters’)
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpfCnpj("12345678901");

        List<Cliente> clientes = List.of(cliente);

        // Simular o método real (sem necessidade de mockar a instância do serviço)
        byte[] excelData = exportService.exportarClientesParaExcel(clientes);

        // Verificar resultados
        assertNotNull(excelData);
        assertTrue(excelData.length > 0);
    }
}
