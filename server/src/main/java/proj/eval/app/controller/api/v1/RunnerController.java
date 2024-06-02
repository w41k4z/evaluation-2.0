package proj.eval.app.controller.api.v1;

import jakarta.validation.Valid;
import java.sql.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.request.CreateRunnerRequest;
import proj.eval.app.service.RunnerService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/runners")
public class RunnerController {

  private RunnerService service;

  public RunnerController(RunnerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<ApiResponse> list(
    @AuthenticationPrincipal UserDetails userDetails
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.service.list(userDetails));
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(
    @RequestBody @Valid CreateRunnerRequest runner
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.service.create(
          runner.getName(),
          runner.getTeamId(),
          runner.getNumber(),
          runner.getGender(),
          Date.valueOf(runner.getBirthDate())
        )
    );
    response.setMessage("Runner created successfully");
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(
    @PathVariable String id,
    @RequestBody @Valid CreateRunnerRequest runner
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.service.update(
          Long.valueOf(id),
          runner.getName(),
          runner.getTeamId(),
          runner.getNumber(),
          runner.getGender(),
          Date.valueOf(runner.getBirthDate())
        )
    );
    response.setMessage("Runner updated successfully");
    return ResponseEntity.ok(response);
  }
}
