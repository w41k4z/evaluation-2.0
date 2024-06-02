import { TestBed } from '@angular/core/testing';

import { AssignRunnerService } from './assign-runner.service';

describe('AssignRunnerService', () => {
  let service: AssignRunnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssignRunnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
