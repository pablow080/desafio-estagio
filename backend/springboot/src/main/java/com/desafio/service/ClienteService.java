public Cliente salvar(Cliente cliente) {
    if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
        throw new RuntimeException("CPF/CNPJ já cadastrado!");
    }
    return clienteRepository.save(cliente);
}