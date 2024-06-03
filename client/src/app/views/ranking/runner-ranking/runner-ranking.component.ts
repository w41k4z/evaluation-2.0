import { Component } from '@angular/core';
import { GeneralRanking } from '../../../models/GeneralRanking';
import { StatService } from '../../../services/stat/stat.service';

@Component({
  selector: 'app-runner-ranking',
  templateUrl: './runner-ranking.component.html',
  styleUrl: './runner-ranking.component.scss',
})
export class RunnerRankingComponent {
  ranking: GeneralRanking[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService) {}

  ngOnInit(): void {
    this.statService.generalRanking().subscribe((response: any) => {
      this.ranking = response.payload;
    });
  }
}
