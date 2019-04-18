import { TestBed } from '@angular/core/testing';

import { StudentsService } from './students.service';
import {HttpClientModule} from "@angular/common/http";

describe('StudentsService', () => {
  beforeEach(() => TestBed.configureTestingModule({

    imports: [
      HttpClientModule
    ],
    providers: [HttpClientModule]
  }));

  it('should be created', () => {
    const service: StudentsService = TestBed.get(StudentsService);
    expect(service).toBeTruthy();
  });
});
