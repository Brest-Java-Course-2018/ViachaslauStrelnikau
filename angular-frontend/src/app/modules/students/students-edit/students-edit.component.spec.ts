import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsEditComponent } from './students-edit.component';
import {CommonModule} from "@angular/common";
import {MaterialModule} from "../../../../../projects/material/src/lib/material.module";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ComponentsModule} from "../../../../../projects/components/src/lib/components.module";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {MY_FORMATS} from "../../../model/formats";

describe('StudentsEditComponent', () => {
  let component: StudentsEditComponent;
  let fixture: ComponentFixture<StudentsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentsEditComponent ],
      imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        HttpClientModule,
        RouterModule,
        BrowserAnimationsModule
      ],
      providers: [ ComponentsModule, HttpClient,
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: {} },
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
        ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
