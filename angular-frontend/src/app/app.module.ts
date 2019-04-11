import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GroupsComponent } from './components/groups/groups.component';
import {HttpClientModule} from "@angular/common/http";
import {GroupServiceService} from "./service/group-service.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "../../projects/material/src/public_api";

@NgModule({
  declarations: [
    AppComponent,
    GroupsComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [GroupServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
