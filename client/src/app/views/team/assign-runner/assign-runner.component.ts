import { Component } from '@angular/core';
import { AssignRunnerService } from '../../../services/assign-runner/assign-runner.service';
import { Stage } from '../../../models/Stage';
import { Runner } from '../../../models/Runner';

@Component({
  selector: 'app-assign-runner',
  templateUrl: './assign-runner.component.html',
  styleUrl: './assign-runner.component.scss',
})
export class AssignRunnerComponent {
  selectedStage: any;
  teamRunners: { runnerId: string }[] = [];
  runners: Runner[] = [];
  stages: Stage[] = [];
  message$ = this.assignRunnerService.message$;
  error$ = this.assignRunnerService.error$;

  constructor(public assignRunnerService: AssignRunnerService) {
    this.addRow();
  }

  ngOnInit(): void {
    this.assignRunnerService.list().subscribe((response) => {
      this.stages = response.payload.stages;
      this.runners = response.payload.runners;
    });
  }

  addRow() {
    this.teamRunners.push({
      runnerId: '',
    });
  }

  submit() {
    if (this.selectedStage && this.teamRunners.length) {
      this.assignRunnerService
        .assign(this.selectedStage, this.teamRunners)
        .subscribe(() => {
          this.teamRunners = [];
          this.addRow();
        });
    }
  }
}
