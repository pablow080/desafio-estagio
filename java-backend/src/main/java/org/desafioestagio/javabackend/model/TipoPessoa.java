package org.desafioestagio.javabackend.model;

public enum TipoPessoa {
    FISICA,
    JURIDICA;

    // Sobrescrevendo o método toString para um formato mais amigável
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    // Método para converter uma String em TipoPessoa
    public static TipoPessoa fromString(String tipo) {
        try {
            return TipoPessoa.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de pessoa inválido: " + tipo);
        }
    }
}
