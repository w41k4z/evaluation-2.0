package proj.eval.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.importation.ImportedPoint;

@Service
public class ImportedPointService {

  public void insertAll(
      List<ImportedPoint> importedPoints,
      Connection connection) throws SQLException {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO points(rank, score) VALUES(");
    for (ImportedPoint importedPoint : importedPoints) {
      sql.append(importedPoint.getRank());
      sql.append(",");
      sql.append(importedPoint.getScore());
      sql.append("), (");
    }
    String query = sql.substring(0, sql.length() - 3);
    connection.createStatement().execute(query);
  }
}
