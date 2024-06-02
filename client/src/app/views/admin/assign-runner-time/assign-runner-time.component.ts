import { Component } from '@angular/core';
import { StageRunner } from '../../../models/StageRunner';
import { StageService } from '../../../services/stage/stage.service';
import { Stage } from '../../../models/Stage';
import { AssignRunnerTimeService } from '../../../services/assign-runner/assign-runner-time.service';

@Component({
  selector: 'app-assign-runner-time',
  templateUrl: './assign-runner-time.component.html',
  styleUrl: './assign-runner-time.component.scss',
})
export class AssignRunnerTimeComponent {
  selectedStage: string = '';
  startTime: string = '';
  endTime: string = '';
  selectedRunner: string = '';
  stageRunners: StageRunner[] = [];
  stages: Stage[] = [];
  loading$ = this.service.loading$;
  message$ = this.service.message$;
  error$ = this.service.error$;

  constructor(
    public service: AssignRunnerTimeService,
    public stageService: StageService
  ) {}

  ngOnInit(): void {
    this.stageService.list().subscribe((response) => {
      this.stages = response.payload;
    });
  }

  search() {
    if (this.selectedStage) {
      this.service.list(this.selectedStage).subscribe((response) => {
        this.stageRunners = response.payload;
      });
    }
  }

  submit() {
    if (this.startTime && this.endTime && this.selectedRunner) {
      this.service
        .assign(this.selectedRunner, this.startTime, this.endTime)
        .subscribe(() => {
          this.stageRunners = [];
          this.selectedStage = '';
          this.selectedRunner = '';
          this.startTime = '';
          this.endTime = '';
        });
    }
  }
}
