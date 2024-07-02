import { Component } from '@angular/core';
import { StatService } from '../../../services/stat/stat.service';
import { TeamRanking } from '../../../models/TeamRanking';
import { ChartConfiguration, ChartData } from 'chart.js';
import { ProfileService } from '../../../services/profile/profile.service';

@Component({
  selector: 'app-team-ranking',
  templateUrl: './team-ranking.component.html',
  styleUrl: './team-ranking.component.scss',
})
export class TeamRankingComponent {
  colors = [
    'text-dark',
    'text-primary',
    'text-danger',
    'text-success',
    'text-warning',
    'text-info',
  ];
  styles: string[] = [];
  ranking: TeamRanking[] = [];
  loading$ = this.statService.loading$;
  error$ = this.statService.error$;
  message$ = this.statService.message$;
  filter = 'Aside';
  filters: { filter: string; value: string }[] = [
    {
      filter: 'Hors catégorie',
      value: 'Aside',
    },
    {
      filter: 'Toutes catégories',
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
  ];

  pieChartOptions: ChartConfiguration<'pie'>['options'] = {
    responsive: true,
  };
  pieChartType = 'pie' as const;
  pieChartData: ChartData<'pie'> = {
    labels: ['None'],
    datasets: [
      {
        data: [0],
        backgroundColor: ['rgb(54, 162, 235)'],
      },
    ],
  };

  constructor(
    public statService: StatService,
    public profileService: ProfileService
  ) {}

  filterByCategory() {
    this.statService
      .teamGlobalRanking(this.filter)
      .subscribe((response: any) => {
        this.ranking = response.payload;
        this.setStyles();
        this.resetChartData();
      });
  }

  ngOnInit(): void {
    this.statService
      .teamGlobalRanking(this.filter)
      .subscribe((response: any) => {
        this.ranking = response.payload;
        this.setStyles();
        this.resetChartData();
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

  resetChartData() {
    let labels = [];
    let data = [];
    for (let team of this.ranking) {
      labels.push(team.team.name);
      data.push(team.totalScore);
    }
    this.pieChartData = {
      labels: labels,
      datasets: [
        {
          data: data,
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(54, 162, 235)',
            'rgb(255, 205, 86)',
            'rgb(153, 102, 255)',
            'rgb(255, 159, 64)',
            'rgb(255, 99, 71)',
            'rgb(229, 233, 240)',
            'rgb(204, 204, 204)',
            'rgb(0, 128, 0)',
          ],
        },
      ],
    };
  }
}
