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
  filter = 'All';
  filters: { filter: string; value: string }[] = [
    {
      filter: 'Tous',
      value: 'All',
    },
    {
      filter: 'Homme',
      value: 'Homme',
    },
    {
      filter: 'Femme',
      value: 'Femme',
    },
    {
      filter: 'Junior',
      value: 'Junior',
    },
    {
      filter: 'Senior',
      value: 'Senior',
    },
  ];

  constructor(public statService: StatService) {}

  filterByCategory() {
    this.statService
      .teamGlobalRanking(this.filter)
      .subscribe((response: any) => {
        this.ranking = response.payload;
      });
  }

  ngOnInit(): void {
    this.statService
      .teamGlobalRanking(this.filter)
      .subscribe((response: any) => {
        this.ranking = response.payload;
      });
  }
}
