import { Component } from '@angular/core';
import { StageService } from '../../services/stage/stage.service';
import { Stage } from '../../models/Stage';
import { ProfileService } from '../../services/profile/profile.service';

@Component({
  selector: 'app-stage-list',
  templateUrl: './stage-list.component.html',
  styleUrl: './stage-list.component.scss',
})
export class StageListComponent {
  stages: Stage[] = [];
  loading$ = this.stageService.loading$;
  error$ = this.stageService.error$;
  message$ = this.stageService.message$;

  constructor(
    public stageService: StageService,
    public profile: ProfileService
  ) {}

  ngOnInit(): void {
    this.stageService.list().subscribe((response) => {
      this.stages = response.payload;
    });
  }
}
