import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../services/cliente.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from './confirm-dialog.component';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  clientes: any[] = [];

  constructor(
    private clienteService: ClienteService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.carregarClientes();
  }

  carregarClientes(): void {
    this.clienteService.listarClientes().subscribe((clientes : any) : void => {
      this.clientes = clientes;
    });
  }

  excluirCliente(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      data: { message: 'Tem certeza que deseja excluir este cliente?' }
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.clienteService.excluirCliente(id).subscribe({
          next: () => {
            this.carregarClientes(); // Recarrega a lista após exclusão
          },
          error: (err : any): void => {
            console.error('Erro ao excluir cliente:', err);
          }
        });
      }
    });
  }
}
