import { Component } from '@angular/core';
import { AssignRunnerService } from '../../../services/assign-runner/assign-runner.service';
import { Stage } from '../../../models/Stage';
import { Runner } from '../../../models/Runner';
import { FormDialogComponent } from './form-dialog/form-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-assign-runner',
  templateUrl: './assign-runner.component.html',
  styleUrl: './assign-runner.component.scss',
})
export class AssignRunnerComponent {
  selectedStage: any;
  runners: Runner[] = [];
  stages: {
    stage: Stage;
    runners: {
      runner: Runner;
      chrono: string;
      finalChrono: string;
      penalty: string;
    }[];
  }[] = [];
  message$ = this.assignRunnerService.message$;
  error$ = this.assignRunnerService.error$;

  constructor(
    public assignRunnerService: AssignRunnerService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.assignRunnerService.list().subscribe((response) => {
      this.runners = response.payload.runners;
      let stages = response.payload.stages;
      this.stages = [];
      for (let stage of stages) {
        this.assignRunnerService.teamRunners(stage.id).subscribe((response) => {
          let runners: {
            runner: Runner;
            chrono: string;
            finalChrono: string;
            penalty: string;
          }[] = [];
          for (let each of response.payload) {
            runners.push({
              runner: each.runner,
              chrono: each.chrono,
              finalChrono: each.finalChrono,
              penalty: each.penalty,
            });
          }
          this.stages.push({ stage, runners: runners });
        });
      }
    });
  }

  openAddDialog(index: number) {
    const dialogRef = this.dialog.open(FormDialogComponent, {
      data: { stageId: this.stages[index].stage.id, runners: this.runners },
    });
    dialogRef.afterClosed().subscribe((teamRunner) => {
      this.assignRunnerService
        .assign(teamRunner.stageId, teamRunner.runnerId)
        .subscribe();
    });
  }
}
