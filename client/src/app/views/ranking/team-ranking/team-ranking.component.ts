import { Component } from '@angular/core';
import { StatService } from '../../../services/stat/stat.service';
import { TeamRanking } from '../../../models/TeamRanking';

@Component({
  selector: 'app-team-ranking',
  templateUrl: './team-ranking.component.html',
  styleUrl: './team-ranking.component.scss',
})
export class TeamRankingComponent {
  ranking: TeamRanking[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService) {}

  ngOnInit(): void {
    this.statService.teamRanking().subscribe((response: any) => {
      this.ranking = response.payload;
    });
  }
}
