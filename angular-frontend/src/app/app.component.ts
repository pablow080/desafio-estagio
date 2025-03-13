import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {MatToolbar} from '@angular/material/toolbar';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatToolbar, MatIcon, MatIconButton],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-frontend';
}
export class PaginatedTableComponent {
  displayedColumns: string[] = ['name'];
  private ELEMENT_DATA: Element | undefined;
  // @ts-ignore
  dataSource = new MatTableDataSource<Element>(this.ELEMENT_DATA);

  // Exemplo de dados
  data = [{ name: 'John' }, { name: 'Jane' }];
}
