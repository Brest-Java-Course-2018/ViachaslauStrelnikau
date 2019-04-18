import {TestBed} from '@angular/core/testing';

import {GroupServiceService} from './group-service.service';
import {HttpClientModule} from "@angular/common/http";

describe('GroupServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({

    imports: [
      HttpClientModule
    ],
    providers: [HttpClientModule]

  }));

  it('should be created', () => {
    const service: GroupServiceService = TestBed.get(GroupServiceService);
    expect(service).toBeTruthy();
  });
});
