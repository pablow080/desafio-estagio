import { Component, Input } from '@angular/core';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {NgForOf, TitleCasePipe} from '@angular/common';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatColumnDef,
    TitleCasePipe,
    MatHeaderRowDef,
    MatRowDef,
    MatHeaderCellDef,
    MatCellDef,
    NgForOf
  ],
  styleUrls: ['./generic-table.component.scss']
})
export class GenericTableComponent {
  @Input() data: any[] = [];
  @Input() displayedColumns: string[] = [];

  constructor() { }
}
