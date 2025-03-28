package org.desafioestagio.backend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public enum TipoPessoa {
    FISICA(
            "Física",
            "CPF",
            "\\d{11}",
            "999.999.999-99",
            11,
            new String[]{"nome", "rg", "dataNascimento"}
    ),

    JURIDICA(
            "Jurídica",
            "CNPJ",
            "\\d{14}",
            "99.999.999/9999-99",
            14,
            new String[]{"razaoSocial", "inscricaoEstadual"}
    );

    private final String descricao;
    private final String documentoLabel;
    private final String documentoRegex;
    private final String documentoMascara;
    private final int documentoTamanho;
    private final String[] camposObrigatorios;

    TipoPessoa(String descricao, String documentoLabel, String documentoRegex,
               String documentoMascara, int documentoTamanho, String[] camposObrigatorios) {
        this.descricao = descricao;
        this.documentoLabel = documentoLabel;
        this.documentoRegex = documentoRegex;
        this.documentoMascara = documentoMascara;
        this.documentoTamanho = documentoTamanho;
        this.camposObrigatorios = camposObrigatorios;
    }

    /**
     * Método para determinar o tipo de pessoa (Física ou Jurídica) baseado no documento.
     * @param documento O documento CPF ou CNPJ.
     * @return O tipo de pessoa correspondente.
     */
    public static TipoPessoa porDocumento(String documento) {
        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("Documento não pode ser nulo ou vazio");
        }
        String docLimpo = documento.replaceAll("\\D", "");
        if (docLimpo.length() != 11 && docLimpo.length() != 14) {
            throw new IllegalArgumentException("Documento inválido");
        }
        return docLimpo.length() == FISICA.documentoTamanho ? FISICA : JURIDICA;
    }


    /**
     * Método para formatar o documento conforme o tipo de pessoa (Física ou Jurídica).
     * @param documento O número do documento (CPF ou CNPJ).
     * @return O número do documento formatado.
     */
    public Optional<String> formatarDocumento(String documento) {
        if (documento == null) return Optional.empty();
        String docLimpo = documento.replaceAll("\\D", "");

        if (docLimpo.length() != documentoTamanho) {
            throw new IllegalArgumentException(documentoLabel + " inválido");
        }

        return Optional.of(switch (this) {
            case FISICA -> docLimpo.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            case JURIDICA -> docLimpo.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        });
    }


    /**
     * Método para verificar se os campos obrigatórios para o tipo de pessoa foram preenchidos.
     * @param campos O conjunto de campos do cliente.
     * @return True se todos os campos obrigatórios estiverem preenchidos.
     */
    public boolean verificarCamposObrigatoriosPreenchidos(String[] campos) {
        Set<String> camposSet = new HashSet<>(Arrays.asList(campos));
        for (String campo : camposObrigatorios) {
            if (!camposSet.contains(campo)) {
                return false;
            }
        }
        return true;
    }
}
