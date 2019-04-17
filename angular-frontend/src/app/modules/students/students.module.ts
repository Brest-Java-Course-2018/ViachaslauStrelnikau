import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentsListComponent } from './students-list/students-list.component';
import { StudentsEditComponent } from './students-edit/students-edit.component';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {MaterialModule} from "../../../../projects/material/src/public_api";
import {DeleteDialogComponent} from "../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {ErrorDialogComponent} from "../../../../projects/components/src/lib/error-dialog/error-dialog.component";


@NgModule({
  declarations: [StudentsListComponent, StudentsEditComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    RouterModule
  ],
  entryComponents: [
    DeleteDialogComponent,
  ErrorDialogComponent,
  StudentsEditComponent]
})
export class StudentsModule { }
