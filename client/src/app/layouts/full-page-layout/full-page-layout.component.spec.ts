import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FullPageLayoutComponent } from './full-page-layout.component';

describe('FullPageLayoutComponent', () => {
  let component: FullPageLayoutComponent;
  let fixture: ComponentFixture<FullPageLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FullPageLayoutComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FullPageLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
