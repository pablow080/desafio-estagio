public class RelatorioService {
    public void gerarRelatorioClientes(List<Cliente> clientes) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("reports/cliente_report.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);
        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        JasperExportManager.exportReportToPdfFile(print, "clientes.pdf");
    }
}