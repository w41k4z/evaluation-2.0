import { Component } from '@angular/core';
import { StageService } from '../../../services/stage/stage.service';
import { Stage } from '../../../models/Stage';
import { ProfileService } from '../../../services/profile/profile.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../../../components/delete-dialog/delete-dialog.component';
import { PenaltyService } from '../../../services/penalty/penalty.service';
import { TeamPenalty } from '../../../models/TeamPenalty';
import { AssignationDialogComponent } from './assignation-dialog/assignation-dialog.component';

@Component({
  selector: 'app-penalty-assignation',
  templateUrl: './penalty-assignation.component.html',
  styleUrl: './penalty-assignation.component.scss',
})
export class PenaltyAssignationComponent {
  teamPenalties: TeamPenalty[] = [];
  teams: { id: string; name: string }[] = [];
  stages: Stage[] = [];
  loading$ = this.penaltyService.loading$;
  error$ = this.penaltyService.error$;
  message$ = this.penaltyService.message$;

  constructor(
    public penaltyService: PenaltyService,
    public profile: ProfileService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.penaltyService.list().subscribe((response) => {
      this.teamPenalties = response.payload.teamPenalties;
      this.stages = response.payload.stages;
      this.teams = response.payload.teams;
    });
  }

  openAppDialog() {
    const dialogRef = this.dialog.open(AssignationDialogComponent, {
      data: {
        stages: this.stages,
        teams: this.teams,
      },
    });

    dialogRef.afterClosed().subscribe((teamPenalty) => {
      if (teamPenalty) {
        this.penaltyService
          .create(
            teamPenalty.stageId,
            teamPenalty.teamId,
            teamPenalty.penaltyTime
          )
          .subscribe((response) => {
            let stage: Stage | null = null;
            let team: { id: string; name: string } | null = null;
            for (let each of this.stages) {
              if (teamPenalty.stageId == each.id) {
                stage = each;
                break;
              }
            }
            for (let each of this.teams) {
              if (teamPenalty.teamId == each.id) {
                team = each;
                break;
              }
            }
            console.log(response.id);
            if (team && stage) {
              this.teamPenalties.push({
                id: response.payload.id,
                stage: stage,
                team: team,
                penalty: response.payload.penalty,
              });
            }
          });
      }
    });
  }

  openDeleteDialog(index: number) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data: this.teamPenalties[index].id,
    });
    dialogRef.afterClosed().subscribe((penaltyIdToDelete) => {
      if (penaltyIdToDelete) {
        this.penaltyService.delete(penaltyIdToDelete).subscribe(() => {
          this.teamPenalties = this.teamPenalties.filter((_, i) => i != index);
        });
      }
    });
  }
}
