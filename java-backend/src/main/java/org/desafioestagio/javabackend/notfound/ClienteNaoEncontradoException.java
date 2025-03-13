package org.desafioestagio.javabackend.notfound;

public class ClienteNaoEncontradoException extends RuntimeException {
    private final Long clienteId;

    public ClienteNaoEncontradoException(Long clienteId) {
        super("Cliente não encontrado com o ID " + clienteId);
        this.clienteId = clienteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
}
