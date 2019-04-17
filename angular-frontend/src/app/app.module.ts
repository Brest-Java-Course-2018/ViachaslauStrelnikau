import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "../../projects/material/src/public_api";
import {ComponentsModule} from "../../projects/components/src/lib/components.module";
import { MDirective } from './m.directive';
import {GroupsModule} from "./modules/groups/groups.module";
import {StudentsModule} from "./modules/students/students.module";
import {ReactiveFormsModule} from "@angular/forms";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {MY_FORMATS} from "./model/formats";
@NgModule({
  declarations: [
    AppComponent,
    MDirective
  ],

  imports: [
    MaterialModule,
    GroupsModule,
    StudentsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ComponentsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
