import { MatDialog } from '@angular/material/dialog';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';

@Component({
  selector: 'app-client-list',
templateUrl: './client-list.component.html',
styleUrls: ['./client-list.component.css']
  })
export class ClientListComponent {

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(ClienteFormComponent, {
      width: '250px',
      data: { nome: 'John' }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
