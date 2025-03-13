import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { Cliente } from '../cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private apiUrl = 'http://localhost:8080/api/clientes';
  private atualizarLista = new Subject<void>();

  constructor(private http: HttpClient) {}

  get atualizarLista$(): Observable<void> {
    return this.atualizarLista.asObservable();
  }

  notificarAtualizacao() {
    this.atualizarLista.next();
  }

  // Obter todos os clientes
  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.apiUrl);
  }

  // Adicionar um cliente
  addCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.apiUrl, cliente).pipe(
      tap(() => this.notificarAtualizacao()) // Notifica após adicionar
    );
  }

  // Editar um cliente
  editCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.apiUrl}/${cliente.id}`, cliente).pipe(
      tap(() => this.notificarAtualizacao()) // Notifica após edição
    );
  }

  // Excluir um cliente
  deleteCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => this.notificarAtualizacao()) // Notifica após exclusão
    );
  }
}
