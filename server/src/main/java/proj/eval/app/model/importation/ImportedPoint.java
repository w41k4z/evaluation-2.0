package proj.eval.app.model.importation;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportedPoint {

  @CsvBindByName(column = "classement")
  private Integer rank;

  @CsvBindByName(column = "points")
  private Integer score;
}
