import { TestBed } from '@angular/core/testing';

import { StepService } from './step.service';

describe('TaskService', () => {
  let service: StepService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StepService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
