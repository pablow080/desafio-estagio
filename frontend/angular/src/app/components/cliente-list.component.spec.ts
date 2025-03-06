it('deve carregar clientes na inicialização', () => {
  spyOn(clienteService, 'listarClientes').and.returnValue(of([mockCliente]));
  fixture.detectChanges();
  expect(component.clientes.length).toBe(1);
});
