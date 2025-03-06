public class ExcelExporter {
    public void exportToExcel(List<Cliente> clientes, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");
        // Preencha as linhas com dados
    }
}