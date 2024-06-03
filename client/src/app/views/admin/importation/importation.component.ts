import { Component } from '@angular/core';
import { ImportationService } from '../../../services/import/importation.service';

@Component({
  selector: 'app-importation',
  templateUrl: './importation.component.html',
  styleUrl: './importation.component.scss',
})
export class ImportationComponent {
  stageFile: File | null = null;
  resultFile: File | null = null;
  pointFile: File | null = null;
  loading$ = this.importationService.loading$;
  error$ = this.importationService.error$;
  message$ = this.importationService.message$;

  constructor(public importationService: ImportationService) {}

  setStageFile(event: any) {
    this.stageFile = event.target.files[0];
  }

  setResultFile(event: any) {
    this.resultFile = event.target.files[0];
  }

  setPointFile(event: any) {
    this.pointFile = event.target.files[0];
  }

  uploadStageFileAndResultFile() {
    if (this.stageFile && this.resultFile) {
      this.importationService
        .uploadStageAndResultFiles(this.stageFile, this.resultFile)
        .subscribe(() => {
          this.stageFile = null;
          this.resultFile = null;
        });
    }
  }

  uploadPointFile() {
    if (this.pointFile) {
      this.importationService.uploadPointFile(this.pointFile).subscribe(() => {
        this.pointFile = null;
      });
    }
  }
}
