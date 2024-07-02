import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Runner } from '../../../../models/Runner';

@Component({
  selector: 'app-form-dialog',
  templateUrl: './form-dialog.component.html',
  styleUrl: './form-dialog.component.scss',
})
export class FormDialogComponent {
  runnerId: string | null = null;
  constructor(
    public dialogRef: MatDialogRef<FormDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public props: {
      stageId: string;
      runners: Runner[];
    }
  ) {}

  confirm() {
    this.dialogRef.close({
      stageId: this.props.stageId,
      runnerId: this.runnerId,
    });
  }
}
