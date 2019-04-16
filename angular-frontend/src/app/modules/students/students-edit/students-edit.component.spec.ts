import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsEditComponent } from './students-edit.component';

describe('StudentsEditComponent', () => {
  let component: StudentsEditComponent;
  let fixture: ComponentFixture<StudentsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentsEditComponent ]
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
