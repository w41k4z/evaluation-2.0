package proj.eval.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.importation.ImportedStage;

@Service
public class ImportedStageService {

  public void importStages(
    List<ImportedStage> importedStages,
    Connection connection
  ) throws SQLException {
    this.insertAll(importedStages, connection);
    connection.createStatement().execute("CALL import_stages()");
  }

  public void insertAll(
    List<ImportedStage> importedStages,
    Connection connection
  ) throws SQLException {
    StringBuilder sql = new StringBuilder(
      "INSERT INTO imported_stages(rank, name, path_length, runners_per_team, start_date, start_time) VALUES("
    );
    for (ImportedStage importedStage : importedStages) {
      sql.append(importedStage.getRank());
      sql.append(",");
      sql.append("'" + importedStage.getName() + "'");
      sql.append(",");
      sql.append(importedStage.getPathLength());
      sql.append(",");
      sql.append(importedStage.getRunnersPerTeam());
      sql.append(",");
      sql.append("'" + importedStage.getStartDate().toString() + "'");
      sql.append(",");
      sql.append("'" + importedStage.getStartTime().toString() + "'");
      sql.append("), (");
    }
    String query = sql.substring(0, sql.length() - 3);
    connection.createStatement().execute(query);
  }
}
