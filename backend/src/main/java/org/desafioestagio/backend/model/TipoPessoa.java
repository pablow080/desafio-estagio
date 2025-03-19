package org.desafioestagio.backend.model;

import lombok.Getter;

@Getter
public enum TipoPessoa {
    FISICA("Pessoa Física"),
    JURIDICA("Pessoa Jurídica");

    // Método para obter a descrição do tipo de pessoa
    private final String descricao;

    // Construtor para associar a descrição com o tipo de pessoa
    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

}
