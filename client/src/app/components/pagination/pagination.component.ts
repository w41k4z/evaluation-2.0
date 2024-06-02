import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss',
})
export class PaginationComponent {
  @Input() public currentPage: number = 1;
  @Input() public totalPages: number = 1;
  @Input() public onPage: (pageNumber: number) => void = () => {};
  public pages: number[] = [];

  ngOnInit(): void {
    for (let index = 0; index < this.totalPages; index++) {
      this.pages.push(index + 1);
    }
  }
}
