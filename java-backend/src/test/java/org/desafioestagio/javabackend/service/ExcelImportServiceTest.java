package org.desafioestagio.javabackend.service;

import static org.junit.jupiter.api.Assertions.*;

import org.desafioestagio.javabackend.model.Cliente;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.FileInputStream;
import java.util.List;

public class ExcelImportServiceTest {

    @InjectMocks
    private ExcelImportService importService;

    @Test
    public void testImportarClientesDeExcel() throws Exception {
        // Usando um arquivo de teste real ou mockado
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/clientes.xlsx");

        // Chama o método real
        List<Cliente> clientesImportados = importService.importarClientesDeExcel(fileInputStream);

        // Verifica os resultados
        assertNotNull(clientesImportados);
        assertFalse(clientesImportados.isEmpty());
    }
}
