import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

@Injectable({
    providedIn: 'root' // Fornece o serviço no nível raiz
})
export class ClienteService {
    constructor(private http: HttpClient) {}

    listarTodos(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>('/api/clientes'); // Endpoint da API
    }
}