export interface Cliente {
    id?: number;
    tipoPessoa: string;
    cpfCnpj: string;
    nome: string;
    rg: string;
    dataNascimento: string;
    razaoSocial: string;
    inscricaoEstadual: string;
    dataCriacao: string;
    email: string;
    ativo: boolean;
    enderecos: string[];
}
