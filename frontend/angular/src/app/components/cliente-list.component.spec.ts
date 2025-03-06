import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClienteListComponent } from './cliente-list.component';
import { ClienteService } from '../services/cliente.service';
import { of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Cliente } from '../models/cliente.model';

describe('ClienteListComponent', () => {
  let component: ClienteListComponent;
  let fixture: ComponentFixture<ClienteListComponent>;
  let clienteService: jasmine.SpyObj<ClienteService>;
  let dialog: jasmine.SpyObj<MatDialog>;

  const mockClientes: Cliente[] = [
    {
      id: 1, nome: 'Cliente 1', cpfCnpj: '12345678900', email: 'cliente1@teste.com', ativo: true,
      tipoPessoa: '',
      rg: '',
      dataNascimento: '',
      razaoSocial: '',
      inscricaoEstadual: '',
      dataCriacao: '',
      enderecos: []
    },
    {
      id: 2, nome: 'Cliente 2', cpfCnpj: '98765432100', email: 'cliente2@teste.com', ativo: false,
      tipoPessoa: '',
      rg: '',
      dataNascimento: '',
      razaoSocial: '',
      inscricaoEstadual: '',
      dataCriacao: '',
      enderecos: []
    }
  ];

  beforeEach(async () => {
    const clienteServiceSpy = jasmine.createSpyObj('ClienteService', ['listarClientes', 'excluirCliente']);
    const dialogSpy = jasmine.createSpyObj('MatDialog', ['open']);

    await TestBed.configureTestingModule({
      declarations: [ClienteListComponent],
      imports: [
        MatTableModule,
        MatIconModule,
        MatButtonModule,
        HttpClientTestingModule
      ],
      providers: [
        { provide: ClienteService, useValue: clienteServiceSpy },
        { provide: MatDialog, useValue: dialogSpy }
      ]
    }).compileComponents();

    clienteService = TestBed.inject(ClienteService) as jasmine.SpyObj<ClienteService>;
    dialog = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClienteListComponent);
    component = fixture.componentInstance;
    clienteService.listarClientes.and.returnValue(of(mockClientes));
    fixture.detectChanges();
  });

  it('deve ser criado', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar clientes na inicialização', () => {
    expect(clienteService.listarClientes).toHaveBeenCalled();
    expect(component.clientes.length).toBe(2);
  });

  it('deve abrir o diálogo de edição', () => {
    const cliente = mockClientes[0];
    component.editarCliente(cliente);
    expect(dialog.open).toHaveBeenCalled();
  });

  it('deve chamar o serviço para excluir cliente', () => {
    spyOn(window, 'confirm').and.returnValue(true);
    component.excluirCliente(1);
    expect(clienteService.excluirCliente).toHaveBeenCalledWith(1);
  });
});
