import { NgModule } from '@angular/core';
import { ComponentsComponent } from './components.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import {MaterialModule} from "../../../material/src/lib/material.module";

@NgModule({
  declarations: [ComponentsComponent, DeleteDialogComponent],
  imports: [MaterialModule
  ],
  exports: [ComponentsComponent,DeleteDialogComponent]
})
export class ComponentsModule { }
