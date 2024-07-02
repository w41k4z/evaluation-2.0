import { Component } from '@angular/core';
import { GeneralRanking } from '../../../models/GeneralRanking';
import { StatService } from '../../../services/stat/stat.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-stage-general-ranking',
  templateUrl: './stage-general-ranking.component.html',
  styleUrl: './stage-general-ranking.component.scss',
})
export class StageGeneralRankingComponent {
  ranking: GeneralRanking[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.statService
      .generalRankingByStage(this.route.snapshot.paramMap.get('stageId') || '')
      .subscribe((response: any) => {
        this.ranking = response.payload;
      });
  }
}
