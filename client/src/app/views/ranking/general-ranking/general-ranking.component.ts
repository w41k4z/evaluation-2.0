import { Component } from '@angular/core';
import { GeneralRanking } from '../../../models/GeneralRanking';
import { StatService } from '../../../services/stat/stat.service';

@Component({
  selector: 'app-general-ranking',
  templateUrl: './general-ranking.component.html',
  styleUrl: './general-ranking.component.scss',
})
export class GeneralRankingComponent {
  ranking: GeneralRanking[] = [];
  groupedRanking: GeneralRanking[][] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService) {}

  ngOnInit(): void {
    this.statService.generalRanking().subscribe((response: any) => {
      this.ranking = response.payload;
      this.groupByStage();
    });
  }

  private groupByStage() {
    let current: GeneralRanking[] = [];
    current.push(this.ranking[0]);
    let currentStage = this.ranking[0].stage.name;
    for (let i = 1; i < this.ranking.length; i++) {
      if (this.ranking[i].stage.name === currentStage) {
        current.push(this.ranking[i]);
      } else {
        currentStage = this.ranking[i].stage.name;
        this.groupedRanking.push(current);
        current = [];
        if (i + 1 < this.ranking.length) {
          current.push(this.ranking[i]);
        }
      }
    }
  }
}
