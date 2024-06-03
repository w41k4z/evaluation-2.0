import { TestBed } from '@angular/core/testing';

import { ImportationService } from './importation.service';

describe('ImportationService', () => {
  let service: ImportationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImportationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
