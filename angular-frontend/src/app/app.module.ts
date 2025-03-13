import { ToastrModule } from 'ngx-toastr';
import {NgModule} from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {AppComponent} from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';

// @ts-ignore
import {NgxMaskModule} from 'ngx-mask';

@NgModule({
  declarations: [],
  imports: [
    ToastrModule.forRoot(),
    HttpClientModule,
    MatToolbarModule,
    NgxMaskModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

