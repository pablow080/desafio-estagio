package org.desafioestagio.javabackend.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.desafioestagio.javabackend.service.ExcelExportService;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ExcelExportServiceTest {

    @Mock
    private ExcelExportService exportService;

    @Test
    public void testExportarClientesParaExcel() throws Exception {
        List<Cliente> clientes = Arrays.asList(new Cliente("John Doe", "12345678901"));
        byte[] excelData = exportService.exportarClientesParaExcel(clientes);

        assertNotNull(excelData);
        assertTrue(excelData.length > 0);
    }
}
