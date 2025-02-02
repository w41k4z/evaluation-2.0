package proj.eval.app.model.importation;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportedResult {

  @CsvBindByName(column = "etape_rang")
  private Integer stageRank;

  @CsvBindByName(column = "numero dossard")
  private Integer runnerNumber;

  @CsvBindByName(column = "nom")
  private String runnerName;

  @CsvBindByName(column = "genre")
  private String runnerStringGender;

  @CsvBindByName(column = "date naissance")
  @CsvDate("dd/MM/yyyy")
  private Timestamp runnerBirthDate;

  @CsvBindByName(column = "equipe")
  private String team;

  @CsvBindByName(column = "arrivée")
  @CsvDate("dd/MM/yyyy hh:mm:ss")
  private Timestamp arrivalTime;

  public static Integer gender(String gender) {
    switch (gender) {
      case "F":
        return 0;
      case "M":
        return 1;
      default:
        throw new RuntimeException("Gender `" + gender + "` unknown");
    }
  }
}
