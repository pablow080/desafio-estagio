import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // Importação do HttpClientModule
import { AppComponent } from './app.component';
import { ClienteListComponent } from './components/cliente-list/cliente-list-component';

@NgModule({
    declarations: [
        AppComponent,
        ClienteListComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}