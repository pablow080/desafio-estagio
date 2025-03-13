import { Component } from '@angular/core';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {FormsModule} from '@angular/forms';
import {MatFormField} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {NgxMaskDirective} from 'ngx-mask';

@Component({
  selector: 'app-cliente-form',
  imports: [
    MatRadioGroup,
    MatRadioButton,
    FormsModule,
    MatFormField,
    MatInput,
    NgxMaskDirective
  ],
  templateUrl: './cliente-form.component.html',
  styleUrl: './cliente-form.component.css'
})
export class ClienteFormComponent {

}
