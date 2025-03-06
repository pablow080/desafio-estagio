import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

@Injectable({
    providedIn: 'root' // Define o escopo do serviço como raiz
})
export class ClienteService {
    private apiUrl = 'http://localhost:8080/api/clientes';
    selectedCliente: boolean | undefined;
    private _cliente: any;

    constructor(private http: HttpClient) { }

    // Método para listar clientes
    listarClientes(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.apiUrl);
    }
    criarCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(this.apiUrl, cliente);
    }
    // (criar, editar, excluir)
    atualizarCliente(cliente: any) {
        this._cliente = cliente;

    }

    excluirCliente(id: number) {

    }
}
