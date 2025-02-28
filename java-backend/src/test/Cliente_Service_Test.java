@Test
public void testSaveCliente() {
    Cliente cliente = new Cliente();
    cliente.setCpf("123.456.789-00");
    assertNotNull(clienteService.save(cliente));
}