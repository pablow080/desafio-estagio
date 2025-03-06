import {Cliente} from './cliente.model';

export interface Endereco {
  id?: number;
  logradouro: string;
  numero: string;
  cep: string;
  bairro: string;
  telefone: string;
  cidade: string;
  estado: string;
  enderecoPrincipal: boolean;
  complemento: string;
  cliente: Cliente;
}
