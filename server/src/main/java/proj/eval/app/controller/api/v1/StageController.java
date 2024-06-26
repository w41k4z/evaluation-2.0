package proj.eval.app.controller.api.v1;

import jakarta.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import proj.eval.app.request.AssignPenaltyRequest;
import proj.eval.app.request.AssignRunnersTimeRequest;
import proj.eval.app.request.CreateStageRequest;
import proj.eval.app.service.RunnerService;
import proj.eval.app.service.StageRunnerService;
import proj.eval.app.service.StageService;
import proj.eval.app.service.TeamPenaltyService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/stages")
public class StageController {

  private StageService service;
  private RunnerService runnerService;
  private StageRunnerService stageRunnerService;
  private TeamPenaltyService teamPenaltyService;

  public StageController(
    StageService service,
    RunnerService runnerService,
    StageRunnerService stageRunnerService,
    TeamPenaltyService teamPenaltyService
  ) {
    this.service = service;
    this.runnerService = runnerService;
    this.stageRunnerService = stageRunnerService;
    this.teamPenaltyService = teamPenaltyService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse> list() {
    ApiResponse response = new ApiResponse();
    response.setPayload(service.list());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/stages-runners")
  public ResponseEntity<ApiResponse> stagesListAndRunners(
    @AuthenticationPrincipal UserDetails userDetails
  ) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("stages", service.list());
    payload.put("runners", runnerService.list(userDetails));
    ApiResponse response = new ApiResponse();
    response.setPayload(payload);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{stageId}/runners")
  public ResponseEntity<ApiResponse> stagesRunners(
    @PathVariable String stageId
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.stageRunnerService.listByStageId(Long.parseLong(stageId))
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(
    @RequestBody @Valid CreateStageRequest stage
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      service.create(
        Date.valueOf(stage.getStartDate()),
        Time.valueOf(stage.getStartTime()),
        stage.getRank(),
        stage.getName(),
        stage.getPathLength(),
        stage.getRunnersPerTeam()
      )
    );
    response.setMessage("Stage created successfully");
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(
    @PathVariable String id,
    @RequestBody @Valid CreateStageRequest stage
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      service.update(
        Long.parseLong(id),
        Date.valueOf(stage.getStartDate()),
        Time.valueOf(stage.getStartTime()),
        stage.getRank(),
        stage.getName(),
        stage.getPathLength(),
        stage.getRunnersPerTeam()
      )
    );
    response.setMessage("Stage updated successfully");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/assign/{id}")
  public ResponseEntity<ApiResponse> assignRunners(
    @AuthenticationPrincipal UserDetails userDetails,
    @PathVariable String id,
    @RequestBody List<Long> runnerIds
  ) {
    ApiResponse response = new ApiResponse();
    service.assignRunners(
      userDetails.getUsername(),
      Long.parseLong(id),
      runnerIds
    );
    response.setMessage("Runners assigned successfully");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/assign-time")
  public ResponseEntity<ApiResponse> assignRunnersTime(
    @RequestBody @Valid AssignRunnersTimeRequest request
  ) {
    ApiResponse response = new ApiResponse();
    service.assignRunnersTime(
      Long.parseLong(request.getStageRunnersId()),
      Timestamp.valueOf(request.getArrivalTime())
    );
    response.setMessage("Runner's time assigned successfully");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/assign-time/penalty/{id}")
  public ResponseEntity<ApiResponse> assignPenalty(
    @PathVariable String id,
    @RequestBody @Valid AssignPenaltyRequest penalty
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.teamPenaltyService.assignPenalty(
      Long.parseLong(id),
      penalty.getTeamId(),
      Time.valueOf(penalty.getPenaltyTime())
    ));
    response.setMessage("Penalty assigned successfully");
    return ResponseEntity.ok(response);
  }
}
