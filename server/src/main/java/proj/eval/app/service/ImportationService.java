package proj.eval.app.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proj.eval.app.model.postgres.importation.ImportedPoint;
import proj.eval.app.model.postgres.importation.ImportedResult;
import proj.eval.app.model.postgres.importation.ImportedStage;
import proj.eval.app.util.ManualConnection;

@Service
public class ImportationService {

  private ManualConnection manualConnection;
  private ImportedPointService importedPointService;
  private ImportedStageService importedStageService;
  private ImportedResultService importedResultService;

  public ImportationService(
    ManualConnection manualConnection,
    ImportedPointService importedPointService,
    ImportedStageService importedStageService,
    ImportedResultService importedResultService
  ) {
    this.manualConnection = manualConnection;
    this.importedPointService = importedPointService;
    this.importedStageService = importedStageService;
    this.importedResultService = importedResultService;
  }

  public void importStagesAndResults(
    MultipartFile stageFile,
    MultipartFile resultFile
  ) throws IOException, SQLException {
    Connection connection = manualConnection.getConnection();
    connection.setAutoCommit(false);

    Reader stageReader = new BufferedReader(
      new InputStreamReader(stageFile.getInputStream())
    );
    Reader resultReader = new BufferedReader(
      new InputStreamReader(resultFile.getInputStream())
    );

    CsvToBean<ImportedStage> importedStage = new CsvToBeanBuilder<ImportedStage>(
      stageReader
    )
      .withType(ImportedStage.class)
      .withSeparator(',')
      .build();
    CsvToBean<ImportedResult> importedResult = new CsvToBeanBuilder<ImportedResult>(
      resultReader
    )
      .withType(ImportedResult.class)
      .withSeparator(',')
      .build();
    List<ImportedStage> importedStages = importedStage.parse();
    List<ImportedResult> importedResults = importedResult.parse();

    try {
      this.importedStageService.importStages(importedStages, connection);
      this.importedResultService.importResults(importedResults, connection);
      connection.commit();
    } catch (SQLException e) {
      connection.rollback();
      throw e;
    } finally {
      connection.close();
    }
  }

  public void importPoints(MultipartFile file)
    throws IOException, SQLException {
    Connection connection = manualConnection.getConnection();
    Reader reader = new BufferedReader(
      new InputStreamReader(file.getInputStream())
    );
    CsvToBean<ImportedPoint> importedPoint = new CsvToBeanBuilder<ImportedPoint>(
      reader
    )
      .withType(ImportedPoint.class)
      .withSeparator(',')
      .build();
    List<ImportedPoint> importedPoints = importedPoint.parse();
    this.importedPointService.insertAll(importedPoints, connection);
    connection.close();
  }
}
