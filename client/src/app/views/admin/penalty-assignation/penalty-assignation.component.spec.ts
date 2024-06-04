import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenaltyAssignationComponent } from './penalty-assignation.component';

describe('PenaltyAssignationComponent', () => {
  let component: PenaltyAssignationComponent;
  let fixture: ComponentFixture<PenaltyAssignationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PenaltyAssignationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PenaltyAssignationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
