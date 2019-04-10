import { TestBed } from '@angular/core/testing';

import { GroupServiceService } from './group-service.service';

describe('GroupServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GroupServiceService = TestBed.get(GroupServiceService);
    expect(service).toBeTruthy();
  });
});
