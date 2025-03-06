import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function cpfCnpjValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) {
      return null;
    }

    const cpfCnpj = value.replace(/\D/g, ''); // Remove caracteres não numéricos
    if (cpfCnpj.length === 11) {
      return validarCPF(cpfCnpj) ? null : { cpfInvalido: true };
    } else if (cpfCnpj.length === 14) {
      return validarCNPJ(cpfCnpj) ? null : { cnpjInvalido: true };
    } else {
      return { cpfCnpjInvalido: true };
    }
  };
}

function validarCPF(cpf: string): boolean {
  // Implemente a lógica de validação de CPF
  return true; // Exemplo simplificado
}

function validarCNPJ(cnpj: string): boolean {
  // Implemente a lógica de validação de CNPJ
  return true; // Exemplo simplificado
}
