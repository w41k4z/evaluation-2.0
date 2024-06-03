package proj.eval.app.model.importation;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportedStage {

  @CsvBindByName(column = "rang")
  private String rank;

  @CsvBindByName(column = "nom")
  private String name;

  @CsvBindByName(column = "km", locale = "fr-FR")
  @CsvNumber("#0,0#")
  private Double pathLength;

  @CsvBindByName(column = "nbcoureur")
  private Integer runnersPerTeam;

  @CsvBindByName(column = "date")
  @CsvDate("dd/MM/yyyy")
  private Date startDate;

  @CsvBindByName(column = "heure")
  @CsvDate("hh:mm:ss")
  private Time startTime;
}
