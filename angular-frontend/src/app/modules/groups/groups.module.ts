import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GroupsListComponent } from './groups-list/groups-list.component';
import {MaterialModule} from "../../../../projects/material/src/public_api";
import { GroupsEditComponent } from './groups-edit/groups-edit.component';

@NgModule({
  declarations: [GroupsListComponent, GroupsEditComponent],
  imports: [
    CommonModule,
    MaterialModule
  ]

})
export class GroupsModule { }
