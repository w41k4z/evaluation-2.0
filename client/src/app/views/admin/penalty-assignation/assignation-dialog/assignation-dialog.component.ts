import { Component, Inject } from '@angular/core';
import { Stage } from '../../../../models/Stage';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-assignation-dialog',
  templateUrl: './assignation-dialog.component.html',
  styleUrl: './assignation-dialog.component.scss',
})
export class AssignationDialogComponent {
  stageId: string | null = null;
  teamId: string | null = null;
  penaltyTime: string = '00:00:00';
  constructor(
    public dialogRef: MatDialogRef<AssignationDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public props: {
      stages: Stage[];
      teams: { id: string; name: string }[];
    }
  ) {}

  confirm() {
    this.dialogRef.close({
      stageId: this.stageId,
      teamId: this.teamId,
      penaltyTime: this.penaltyTime,
    });
  }
}
