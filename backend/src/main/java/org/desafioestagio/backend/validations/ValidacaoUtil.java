package org.desafioestagio.backend.validations;

public class ValidacaoUtil {

    private static int calcularDigitoVerificador(int[] digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i] * weights[i];
        }
        int digit = 11 - (sum % 11);
        return digit >= 10 ? 0 : digit;
    }

    public static boolean isCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] digits = cpf.chars().map(Character::getNumericValue).toArray();
        int firstDigit = calcularDigitoVerificador(digits, new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2});
        int secondDigit = calcularDigitoVerificador(digits, new int[] {11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return digits[9] == firstDigit && digits[10] == secondDigit;
    }

    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] digits = cnpj.chars().map(Character::getNumericValue).toArray();
        int firstDigit = calcularDigitoVerificador(digits, new int[] {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int secondDigit = calcularDigitoVerificador(digits, new int[] {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        return digits[12] == firstDigit && digits[13] == secondDigit;
    }
}
