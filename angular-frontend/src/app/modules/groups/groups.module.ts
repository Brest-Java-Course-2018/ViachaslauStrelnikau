import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GroupsListComponent } from './groups-list/groups-list.component';
import {MaterialModule} from "../../../../projects/material/src/public_api";
import { GroupsEditComponent } from './groups-edit/groups-edit.component';
import {DeleteDialogComponent} from "../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [GroupsListComponent, GroupsEditComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    RouterModule
  ],
  entryComponents: [
    DeleteDialogComponent,
    GroupsEditComponent
  ]

})
export class GroupsModule { }
