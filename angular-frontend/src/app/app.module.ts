import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {GroupServiceService} from "./service/group-service.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "../../projects/material/src/public_api";
import {DeleteDialogComponent} from "../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {ComponentsModule} from "../../projects/components/src/lib/components.module";
import { MDirective } from './m.directive';
import {GroupsListComponent} from "./modules/groups/groups-list/groups-list.component";
import {GroupsEditComponent} from "./modules/groups/groups-edit/groups-edit.component";
@NgModule({
  declarations: [
    AppComponent,
    MDirective,
    GroupsListComponent,
    GroupsEditComponent
  ],
  entryComponents: [
    DeleteDialogComponent,
    GroupsEditComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ComponentsModule
  ],
  providers: [GroupServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
