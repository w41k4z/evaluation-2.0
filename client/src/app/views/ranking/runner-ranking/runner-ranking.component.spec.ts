import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RunnerRankingComponent } from './runner-ranking.component';

describe('RunnerRankingComponent', () => {
  let component: RunnerRankingComponent;
  let fixture: ComponentFixture<RunnerRankingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RunnerRankingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RunnerRankingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
