import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignRunnerTimeComponent } from './assign-runner-time.component';

describe('AssignRunnerTimeComponent', () => {
  let component: AssignRunnerTimeComponent;
  let fixture: ComponentFixture<AssignRunnerTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignRunnerTimeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignRunnerTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
