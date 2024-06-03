package proj.eval.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import proj.eval.app.service.ImportationService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/admin/imports")
public class ImportController {

  private ImportationService importationService;

  public ImportController(ImportationService service) {
    this.importationService = service;
  }

  @PostMapping("/stages-results")
  public ResponseEntity<ApiResponse> importStagesAndResults(
    @RequestParam("files") MultipartFile[] files
  ) throws IOException, SQLException {
    ApiResponse response = new ApiResponse();
    if (!files[0].isEmpty() && !files[0].isEmpty()) {
      this.importationService.importStagesAndResults(files[0], files[1]);
      response.setMessage("Stage and results imported successfully");
      return ResponseEntity.ok(response);
    }
    response.setErrors(
      List.of(
        "Please, provide two valid csv file for the stage csv and the result csv"
      )
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/points")
  public ResponseEntity<ApiResponse> importPoints(
    @RequestParam("file") MultipartFile file
  ) throws IOException, SQLException {
    ApiResponse response = new ApiResponse();
    if (!file.isEmpty()) {
      this.importationService.importPoints(file);
      response.setMessage("Points imported successfully");
      return ResponseEntity.ok(response);
    }
    response.setErrors(
      List.of(
        "Please, provide two valid csv file for the stage csv and the result csv"
      )
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
