import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrl: './delete-dialog.component.scss',
})
export class DeleteDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {
    if (!this.data) {
      this.data = '';
    }
  }

  confirm(): void {
    this.dialogRef.close(this.data);
  }

  close(): void {
    this.dialogRef.close();
  }
}
