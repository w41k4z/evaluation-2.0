package proj.eval.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.importation.ImportedResult;

@Service
public class ImportedResultService {

  public void importResults(
      List<ImportedResult> importedResults,
      Connection connection) throws SQLException {
    this.insertAll(importedResults, connection);
    connection.createStatement().execute("CALL import_results()");
  }

  public void insertAll(
      List<ImportedResult> importedResults,
      Connection connection) throws SQLException {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO imported_results(stage_rank, runner_number, runner_name, runner_gender, runner_birth_date, team, arrival_time) VALUES(");
    for (ImportedResult importedResult : importedResults) {
      sql.append(importedResult.getStageRank());
      sql.append(",");
      sql.append(importedResult.getRunnerNumber().replaceAll("'", "''"));
      sql.append(",");
      sql.append(
          "'" + importedResult.getRunnerName().replaceAll("'", "''") + "'");
      sql.append(",");
      sql.append(ImportedResult.gender(importedResult.getRunnerStringGender()));
      sql.append(",");
      sql.append("'" + importedResult.getRunnerBirthDate() + "'");
      sql.append(",");
      sql.append("'" + importedResult.getTeam().replaceAll("'", "''") + "'");
      sql.append(",");
      sql.append("'" + importedResult.getArrivalTime().toString() + "'");
      sql.append("), (");
    }
    String query = sql.substring(0, sql.length() - 3);
    connection.createStatement().execute(query);
  }
}
