import { NgModule } from '@angular/core';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import {MaterialModule} from "../../../material/src/lib/material.module";
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';

@NgModule({
  declarations: [ DeleteDialogComponent, ErrorDialogComponent],
  imports: [MaterialModule
  ],
  exports: [DeleteDialogComponent]
})
export class ComponentsModule { }
