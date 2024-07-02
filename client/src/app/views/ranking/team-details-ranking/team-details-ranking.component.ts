import { Component } from '@angular/core';
import { TeamRankingDetails } from '../../../models/TeamRankingDetails';
import { StatService } from '../../../services/stat/stat.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-team-details-ranking',
  templateUrl: './team-details-ranking.component.html',
  styleUrl: './team-details-ranking.component.scss',
})
export class TeamDetailsRankingComponent {
  ranking: TeamRankingDetails[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.statService
      .teamRankingDetails(this.route.snapshot.paramMap.get('teamId') || '')
      .subscribe((response: any) => {
        this.ranking = response.payload;
      });
  }
}
