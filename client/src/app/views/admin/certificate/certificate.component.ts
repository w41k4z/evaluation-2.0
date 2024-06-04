import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CertificateService } from '../../../services/certificate/certificate.service';

declare let html2pdf: any;

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrl: './certificate.component.scss',
})
export class CertificateComponent {
  category: string = '';
  winner: {
    id: number;
    name: string;
  } | null = null;

  constructor(
    private route: ActivatedRoute,
    public certificateService: CertificateService
  ) {}

  ngOnInit(): void {
    switch (this.route.snapshot.paramMap.get('category')) {
      case 'Aside':
        this.category = 'Hors catégorie';
        break;
      case 'All':
        this.category = 'Toutes catégories';
        break;
      case 'Homme':
        this.category = 'Homme';
        break;
      case 'Femme':
        this.category = 'Femme';
        break;
      case 'Junior':
        this.category = 'Junior';
        break;
      default:
        this.category = '*';
        break;
    }
    let filter = this.route.snapshot.paramMap.get('category') || 'Aside';
    this.certificateService.getWinner(filter).subscribe((response) => {
      this.winner = response.payload;
      this.exportPdf();
    });
  }

  exportPdf() {
    let data = document.getElementById('certificate');
    if (data) {
      const options = {
        filename: 'Certificat.pdf',
        margin: 1,
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'in', format: 'letter', orientation: 'landscape' },
      };

      html2pdf().set(options).from(data).save();
    }
  }
}
