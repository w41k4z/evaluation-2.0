import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StageAssignationComponent } from './stage-assignation.component';

describe('StageAssignationComponent', () => {
  let component: StageAssignationComponent;
  let fixture: ComponentFixture<StageAssignationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StageAssignationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StageAssignationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
