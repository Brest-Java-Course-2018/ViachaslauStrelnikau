import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GroupsComponent } from './components/groups/groups.component';
import {HttpClientModule} from "@angular/common/http";
import {GroupServiceService} from "./service/group-service.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule, MatIconModule, MatListModule, MatToolbarModule} from "@angular/material";

@NgModule({
  declarations: [
    AppComponent,
    GroupsComponent
  ],
  imports: [
    MatCardModule,
    MatListModule,
    MatIconModule,
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [GroupServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
