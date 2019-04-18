import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsListComponent } from './students-list.component';
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {MaterialModule} from "../../../../../projects/material/src/lib/material.module";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {MY_FORMATS} from "../../../model/formats";

describe('StudentsListComponent', () => {
  let component: StudentsListComponent;
  let fixture: ComponentFixture<StudentsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentsListComponent ],
      imports: [
        CommonModule,
        HttpClientModule,
        MaterialModule,
        FormsModule,
        RouterModule.forRoot([]),
        BrowserAnimationsModule
      ],
      providers: [HttpClientModule,
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
