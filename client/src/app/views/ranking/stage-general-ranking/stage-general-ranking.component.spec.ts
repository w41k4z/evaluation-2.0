import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StageGeneralRankingComponent } from './stage-general-ranking.component';

describe('StageGeneralRankingComponent', () => {
  let component: StageGeneralRankingComponent;
  let fixture: ComponentFixture<StageGeneralRankingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StageGeneralRankingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StageGeneralRankingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
