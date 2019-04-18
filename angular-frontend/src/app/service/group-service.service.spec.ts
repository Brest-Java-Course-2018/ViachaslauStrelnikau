import {TestBed} from '@angular/core/testing';

import {GroupServiceService} from './group-service.service';
import {HttpClientModule} from "@angular/common/http";
import {GroupDto} from "../model/group-dto";

describe('GroupServiceService', () => {

  const groups:GroupDto[]=[];
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

  it('should use ValueService', () => {
    const service: GroupServiceService = TestBed.get(GroupServiceService);
    expect(typeof service.getGroups()).toBe(typeof groups);
  });
});
