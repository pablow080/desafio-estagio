import { Component } from '@angular/core';
import {
  MatCell, MatCellDef, MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from '@angular/material/table';
import {CdkTableDataSourceInput} from '@angular/cdk/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-paginated-table',
  imports: [
    MatTable,
    MatHeaderCell,
    MatHeaderRow,
    MatRow,
    MatPaginator,
    MatCell,
    MatRowDef,
    MatHeaderRowDef,
    MatCellDef,
    MatHeaderCellDef,
    MatColumnDef
  ],
  templateUrl: './paginated-table.component.html',
  styleUrl: './paginated-table.component.css'
})
export class PaginatedTableComponent {
  dataSource: CdkTableDataSourceInput<any> | undefined;
  displayedColumns: any;
  data: unknown;

}
