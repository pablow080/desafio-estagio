import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './components/app/app.component'; // Importação do componente

@NgModule({
    declarations: [
        // Remova AppComponent daqui
    ],
    imports: [
        BrowserModule,
        AppComponent // Adicione aqui
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}
