import { Component } from '@angular/core'; // Importação do Component
import { Cliente } from '../../models/cliente.model'; // Importação da interface Cliente
import { ClienteService } from '../../services/cliente.service';
@Component({
    selector: 'app-cliente-list',
    templateUrl: './cliente-list.component.html',
    styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent {
    clientes: Cliente[] = []; // Definição do tipo Cliente[]

    constructor(private clienteService: ClienteService) {
        this.carregarClientes();
    }

    carregarClientes() {
        this.clienteService.listarTodos().subscribe((data: Cliente[]) => {
            this.clientes = data; // Definição do tipo do parâmetro data
        });
    }

    editar(cliente: Cliente) {
        // Lógica para editar cliente
        console.log('Editar cliente:', cliente);
    }
}