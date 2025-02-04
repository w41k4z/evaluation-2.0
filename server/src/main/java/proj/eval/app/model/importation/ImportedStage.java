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

  @CsvBindByName(column = "etape")
  private String name;

  @CsvBindByName(column = "longueur", locale = "fr-FR")
  @CsvNumber("#0,0#")
  private Double pathLength;

  @CsvBindByName(column = "nb coureur")
  private Integer runnersPerTeam;

  @CsvBindByName(column = "date départ")
  @CsvDate("dd/MM/yyyy")
  private Date startDate;

  @CsvBindByName(column = "heure départ")
  @CsvDate("hh:mm:ss")
  private Time startTime;
}
