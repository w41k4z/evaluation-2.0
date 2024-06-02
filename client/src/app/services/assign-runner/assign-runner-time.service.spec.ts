import { TestBed } from '@angular/core/testing';

import { AssignRunnerTimeService } from './assign-runner-time.service';

describe('AssignRunnerTimeService', () => {
  let service: AssignRunnerTimeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssignRunnerTimeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
