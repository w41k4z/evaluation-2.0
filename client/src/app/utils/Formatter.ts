export default class Formatter {
  public formatDate = (date: Date | undefined): string => {
    if (date) {
      date = new Date(date);
      const options: Intl.DateTimeFormatOptions = {
        day: 'numeric',
        month: 'long',
        year: 'numeric',
      };
      return date.toLocaleDateString('fr-FR', options);
    }
    return this.formatDate(new Date());
  };

  public formatCurrency = (value: number | undefined): string => {
    if (value) {
      const options: Intl.NumberFormatOptions = {
        style: 'decimal',
        useGrouping: true,
        minimumFractionDigits: 0,
        maximumFractionDigits: 0,
      };

      const formatted = new Intl.NumberFormat('fr-FR', options).format(value);

      return `${formatted} Ar`;
    }
    return '0.0';
  };
}
