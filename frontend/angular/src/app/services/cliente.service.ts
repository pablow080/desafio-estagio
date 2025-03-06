import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

@Injectable({
    providedIn: 'root' // O serviço é fornecido no nível raiz do aplicativo
})
export class ClienteService {
    private apiUrl = 'http://localhost:8080/api/clientes'; // URL da API
    selectedCliente: boolean | undefined;

    constructor(private http: HttpClient) {}

    // Método para listar todos os clientes
    listarClientes(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.apiUrl);
    }

    // Método para buscar um cliente por ID
    buscarClientePorId(id: number): Observable<Cliente> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<Cliente>(url);
    }

    // Método para criar um novo cliente
    criarCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(this.apiUrl, cliente);
    }

    // Método para atualizar um cliente existente
    atualizarCliente(cliente: Cliente): Observable<Cliente> {
        const url = `${this.apiUrl}/${cliente.id}`;
        return this.http.put<Cliente>(url, cliente);
    }

    // Método para excluir um cliente
    excluirCliente(id: number): Observable<void> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.delete<void>(url);
    }
}
