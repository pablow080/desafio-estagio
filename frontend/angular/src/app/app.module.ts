import { HttpClientModule } from '@angular/common/http';
import {NgModule} from "@angular/core"; // Adicione esta linha

let AppComponent: any;
let BrowserModule: any;

@NgModule({
    declarations: [
        // Seus componentes
    ],
    imports: [
        BrowserModule,
        HttpClientModule, // Adicione o HttpClientModule aqui
        // Outros módulos
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }