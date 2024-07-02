import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsRankingComponent } from './team-details-ranking.component';

describe('TeamDetailsRankingComponent', () => {
  let component: TeamDetailsRankingComponent;
  let fixture: ComponentFixture<TeamDetailsRankingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TeamDetailsRankingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TeamDetailsRankingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
