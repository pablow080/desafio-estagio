public Endereco salvar(Endereco endereco) {
    if (enderecoRepository.existsByCpfCnpj(endereco.getCep())) {
        throw new RuntimeException("CEP já cadastrado!");
    }
    return enderecoRepository.save(endereco);
}