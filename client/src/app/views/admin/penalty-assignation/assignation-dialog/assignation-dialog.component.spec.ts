import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignationDialogComponent } from './assignation-dialog.component';

describe('AssignationDialogComponent', () => {
  let component: AssignationDialogComponent;
  let fixture: ComponentFixture<AssignationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignationDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
