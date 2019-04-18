import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupsEditComponent } from './groups-edit.component';
import {CommonModule} from "@angular/common";
import {MaterialModule} from "../../../../../projects/material/src/lib/material.module";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ComponentsModule} from "../../../../../projects/components/src/public_api";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

describe('GroupsEditComponent', () => {
  let component: GroupsEditComponent;
  let fixture: ComponentFixture<GroupsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupsEditComponent ],
      imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        RouterModule,
        BrowserAnimationsModule
      ],
      providers: [ ComponentsModule,
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: {} }]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
