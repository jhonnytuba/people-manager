import { NgModule, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PeopleComponent } from './people/people.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpTokenInterceptor } from './core/interceptors/http-token.interceptor';

import localeBR from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { PersonGenderPipe } from './core/pipes/person-gender.pipe';
import { DeletePersonModalTemplateComponent } from './people/delete-person-modal-template.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { SavePersonModalTemplateComponent } from './people/save-person-modal-template.component';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { SourceComponent } from './source/source.component';

registerLocaleData(localeBR);

@NgModule({
  declarations: [
    PersonGenderPipe,
    DeletePersonModalTemplateComponent,
    SavePersonModalTemplateComponent,
    AppComponent,
    PeopleComponent,
    SourceComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    SweetAlert2Module.forRoot(),
    AppRoutingModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true},
    { provide: LOCALE_ID, useValue: 'pt' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
