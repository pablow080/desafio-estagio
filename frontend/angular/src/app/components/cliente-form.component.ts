import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ClienteService } from '../services/cliente.service';
import { Cliente } from '../models/cliente.model';
import { cpfCnpjValidator } from './cpf-cnpj.validator';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css']
})
export class ClienteFormComponent implements OnInit {
  clienteForm: FormGroup;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService,
    private dialogRef: MatDialogRef<ClienteFormComponent>
  ) {
    this.clienteForm = this.fb.group({
      id: [null],
      nome: ['', Validators.required],
      cpfCnpj: ['', [Validators.required, cpfCnpjValidator()]],
      email: ['', [Validators.required, Validators.email]],
      ativo: [true]
    });
  }

  ngOnInit(): void {
    if (this.clienteService.selectedCliente) {
      this.isEditMode = true;
      // @ts-ignore
      this.clienteForm.patchValue(this.clienteService.selectedCliente);
    }
  }

  onSubmit(): void {
    if (this.clienteForm.invalid) {
      return;
    }

    const cliente = this.clienteForm.value;
    if (this.isEditMode) {
      this.clienteService.atualizarCliente(cliente).subscribe(() => {
        this.dialogRef.close(true);
      });
    } else {
      this.clienteService.criarCliente(cliente).subscribe(() => {
        this.dialogRef.close(true);
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }
}
