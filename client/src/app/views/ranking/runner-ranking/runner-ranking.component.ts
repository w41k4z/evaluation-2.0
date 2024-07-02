import { Component } from '@angular/core';
import { GeneralRanking } from '../../../models/GeneralRanking';
import { StatService } from '../../../services/stat/stat.service';

@Component({
  selector: 'app-runner-ranking',
  templateUrl: './runner-ranking.component.html',
  styleUrl: './runner-ranking.component.scss',
})
export class RunnerRankingComponent {
  colors = [
    'text-dark',
    'text-primary',
    'text-danger',
    'text-success',
    'text-warning',
    'text-info',
  ];
  styles: string[] = [];
  ranking: GeneralRanking[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;

  constructor(public statService: StatService) {}

  ngOnInit(): void {
    this.statService.generalRanking().subscribe((response: any) => {
      this.ranking = response.payload;
      this.setStyles();
    });
  }

  setStyles() {
    this.styles = [];
    let colorIndex = 0;
    let styleIndex = 0;
    let currentColor = this.colors[colorIndex++];
    this.styles[styleIndex++] = currentColor;
    let currentRank = this.ranking[0].rank;
    for (let index = 1; index < this.ranking.length; index++) {
      if (currentRank != this.ranking[index].rank) {
        currentColor = this.colors[colorIndex++];
      }
      this.styles[styleIndex++] = currentColor;
      currentRank = this.ranking[index].rank;
      if (colorIndex == this.colors.length) {
        colorIndex = 0;
      }
    }
  }
}
