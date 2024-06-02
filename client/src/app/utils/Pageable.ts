import Formatter from './Formatter';

export class Pageable extends Formatter {
  constructor(
    public currentPage: number,
    public numberOfElements: number,
    public totalPages: number,
    public filters: { filter: string; value: string }[] = []
  ) {
    super();
  }

  nextPage() {
    return this.currentPage + 1 > this.totalPages ? 1 : this.currentPage + 1;
  }

  previousPage() {
    return this.currentPage - 1 < 1 ? this.totalPages : this.currentPage - 1;
  }
}
