import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignRunnerComponent } from './assign-runner.component';

describe('AssignRunnerComponent', () => {
  let component: AssignRunnerComponent;
  let fixture: ComponentFixture<AssignRunnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignRunnerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignRunnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
